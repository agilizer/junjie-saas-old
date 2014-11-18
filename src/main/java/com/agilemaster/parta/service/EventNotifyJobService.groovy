package cn.com.agilemaster

import cn.com.agilemaster.cloud.SmsService
import cn.com.agilemaster.junjie.StaticField
import cn.com.agilemaster.junjie.StaticMethod
import com.alibaba.fastjson.JSON
import org.quartz.CronScheduleBuilder
import org.quartz.CronTrigger
import org.quartz.Job
import org.quartz.JobDataMap
import org.quartz.JobExecutionContext
import org.quartz.JobExecutionException
import org.quartz.JobKey
import org.quartz.SchedulerException
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.DateBuilder.*;

import java.util.Date;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SchedulerMetaData;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;

class EventNotifyJobService {
    final static String SMS_URL_PATH="s/e/"
    static lazyInit = true
    def smsService
    def sessionFactory
    def junjieConfigService
    private static  Scheduler scheduler;
    private static final JOB_NAME_START="notifyId-"
    private static final JOB_GROUP="eventNotifyJob"
    private int workStartTime = 9;
    private int workEndTime = 18;


    /**
     * 创建一个调度对象
     * @return
     * @throws org.quartz.SchedulerException
     */
    @PostConstruct
    public void init(){
        SchedulerFactory sf = new StdSchedulerFactory();
        workStartTime = junjieConfigService.getConfig(StaticField.OFFICE_START_TIME)
        workEndTime = junjieConfigService.getConfig(StaticField.OFFICE_END_TIME)
        try {
            scheduler = sf.getScheduler();
            EventNotify.list().each {
                addEventNotify(it)
            }
            scheduler.start();
        } catch (SchedulerException e) {
            log.error("start evetnNotifyJobService scheduler",e)
        }
    }
    @PreDestroy
    public void destory(){
        if(scheduler!=null){
            scheduler.shutdown()
        }
    }
    def sendEventSms(Event event,String action){
        def content = ""
        def template =  junjieConfigService.getConfig(StaticField.SMS_TEMPLATE_EVENT).toString()
        template = template.replaceAll(StaticField.smsTemplateVar.SMS_T_USERNAME.getValue(),event.author.fullName)
        template = template.replaceAll(StaticField.smsTemplateVar.SMS_T_START_DATE.getValue(),event.startDate.format("MM-dd"))
        template = template.replaceAll(StaticField.smsTemplateVar.SMS_T_END_DATE.getValue(),event.endDate.format("MM-dd"))
        template = template.replaceAll(StaticField.smsTemplateVar.SMS_T_TITLE.getValue(),StaticMethod.lenECString(event.title,12))
        template = template.replaceAll(StaticField.smsTemplateVar.SMS_T_ACTION.getValue(),action)
        template = template.replaceAll(StaticField.smsTemplateVar.SMS_T_URL.getValue(),junjieConfigService.getConfig(StaticField.SERVER_URL)
                +SMS_URL_PATH+event.id)
        event.participants.each {User user->
            content =  template.replaceAll(StaticField.smsTemplateVar.SMS_T_RECEIVE_USERNAME.getValue(),user.fullName)
            smsService.sendSms(user.telephone,content)
        }
        if(event.masterUser){
            content =  template.replaceAll(StaticField.smsTemplateVar.SMS_T_RECEIVE_USERNAME.getValue(),event.masterUser.fullName)
            smsService.sendSms(event.masterUser.telephone,content)
        }
    }

    public void sendNotify(Long notifyId){
        def session
        try{
            session = sessionFactory.openSession()
            EventNotify eventNotify = session.get(EventNotify.class,notifyId)
            if(eventNotify){
                def event = eventNotify.event
                if(event&&event.progress<100){
                    def calendar = Calendar.getInstance()
                    def hour =  calendar.get(Calendar.HOUR_OF_DAY)
                    if(hour>workStartTime&&hour<workEndTime){
                        def action =     "提醒"
                        if(calendar.getTime()>event.endDate){
                            action = "超时"
                            deleteNotify(eventNotify.id)
                        }
                        sendEventSms(event,action)
                    }
                }
                if(event&&event.progress==100){
                    deleteNotify(eventNotify.id)
                }
            }else{
                log.error("sendNotify not found eventNotify notifyId:"+notifyId)
                deleteNotify(notifyId)
            }
        }catch (e){
            log.error("sendNotify error notifyId:"+notifyId,e)
        }finally{
            if(session!=null){
                session.close()
            }
        }
    }
    public void deleteNotify(EventNotify eventNotify){
        if(eventNotify!=null){
            deleteNotify(eventNotify.id)
        }else{
            log.warn("eventNotify is null")
        }
    }
    public void deleteNotify(Long eventNotifyId){
        if(eventNotifyId!=null){
            JobKey jobKey = new JobKey(JOB_NAME_START+eventNotifyId,JOB_GROUP)
            scheduler.deleteJob(jobKey)
        }else{
            log.warn("eventNotify is null")
        }
    }
    public void updateNotify(EventNotify eventNotify){
        if(eventNotify!=null){
            JobKey jobKey = new JobKey(JOB_NAME_START+eventNotify.id,JOB_GROUP)
            scheduler.deleteJob(jobKey)
            addEventNotify(eventNotify)
        }else{
            log.warn("eventNotify is null")
        }
    }
    public void addEventNotify(EventNotify eventNotify){
        if(eventNotify!=null){
            JobDetail job = newJob(EventNotifyJob.class)
                    .withIdentity(JOB_NAME_START+eventNotify.id, JOB_GROUP)
                    .build();
            job.getJobDataMap().put(EventNotifyJob.NOTIFY_KEY, eventNotify.id);
            job.getJobDataMap().put(EventNotifyJob.EVENT_NOTIFY_JOB_SERVICE, this);
            def notifyValueMap = JSON.parseObject(eventNotify.notifyValue,HashMap.class)
            def event  =  eventNotify.event
            if(event.endDate<(new Date())){
                log.warn("event stop time is before now!!")
                return
            }
            switch (eventNotify.eventNotifyType){
                case(EventNotifyType.EVERY_DAY):
                    def hour = notifyValueMap.get("hour") as int
                    def minute =  notifyValueMap.get("minute") as int
                    def stopTime = new Date(eventNotify.event.endDate.getTime()+ StaticField.ONE_DATE_LONG)
                    log.info(stopTime)
                    CronTrigger trigger = newTrigger()
                            .withIdentity(JOB_NAME_START+eventNotify.id, JOB_GROUP)
                            .startAt(new Date())
                            .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(hour,minute))
                            .endAt(stopTime)
                            .build();
                    log.info("add eventNotify(EVERY_DAY) job ok id:"+eventNotify.id)
                    scheduler.scheduleJob(job, trigger);
                    break;
                case(EventNotifyType.ONCE):
                    def notifyDate = StaticMethod.parseDate(notifyValueMap.get("time"),'yyyy-MM-dd HH:mm:ss')
                    if(notifyDate>(new Date())){
                        SimpleTrigger trigger = newTrigger()
                                .withIdentity(JOB_NAME_START+eventNotify.id, JOB_GROUP)
                                .startAt(notifyDate)
                                .withSchedule(simpleSchedule()
                                .withIntervalInSeconds(5)
                                .withRepeatCount(1))
                                .build();
                        log.info("add eventNotify(ONCE) job ok id:"+eventNotify.id)
                        scheduler.scheduleJob(job, trigger);
                    }
                    break;
                case(EventNotifyType.EVERY_WEEK):
                    break;
            }
        }else{
            log.warn("eventNotify is null")
        }
    }

}
class EventNotifyJob implements Job{
    private static Logger _log = LoggerFactory.getLogger(EventNotifyJob.class);
    public final static String NOTIFY_KEY="notifyId"
    public final static String EVENT_NOTIFY_JOB_SERVICE="EventNotifyJobService"
    /**
     * <p>
     * Empty constructor for job initilization
     * </p>
     * <p>
     * Quartz requires a public empty constructor so that the
     * scheduler can instantiate the class whenever it needs.
     * </p>
     */
    public EventNotifyJob() {
    }

    /**
     * <p>
     * Called by the <code>{@link org.quartz.Scheduler}</code> when a
     * <code>{@link org.quartz.Trigger}</code> fires that is associated with
     * the <code>Job</code>.
     * </p>
     *
     * @throws JobExecutionException
     *             if there is an exception while executing the job.
     */
    public void execute(JobExecutionContext context)
    throws JobExecutionException {
        JobKey jobKey = context.getJobDetail().getKey();
        // Grab and print passed parameters
        JobDataMap data = context.getJobDetail().getJobDataMap();
        // Say Hello to the World and display the date/time
        _log.info("jobKey---->name:" + jobKey.getName()+",group:"+jobKey.getGroup()+"  data:"+data.get(NOTIFY_KEY));
        def jobService =  (EventNotifyJobService)data.get(EVENT_NOTIFY_JOB_SERVICE)
        jobService.sendNotify(data.get(NOTIFY_KEY))
    }
}
