package cn.com.agilemaster

import cn.com.agilemaster.junjie.StaticField
import cn.com.agilemaster.junjie.StaticMethod
import com.alibaba.fastjson.JSON
import org.apache.shiro.SecurityUtils
import org.quartz.SimpleTrigger
import org.quartz.Trigger

import javax.servlet.http.HttpSession

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.DateBuilder.*;

import javax.xml.bind.ValidationException
import java.text.SimpleDateFormat

class EventService {

    def junjieConfigService
    def systemLogService
    def eventNotifyJobService
    def ajaxPaginateService
    def junjieUserService
    def userLogService
    static SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMM yyyy HH: mm: ss 'GMT'Z", Locale.ENGLISH)
    static SimpleDateFormat formatterShowStart = new SimpleDateFormat("EEE, d MMM yyyy  'GMT'Z", Locale.ENGLISH)
    Event persistEvent(params) {
        def event = new Event(params)
        try {
            if (event.save(flush: true, failOnError: true)) {
                //gen eventProgress
                if(event.separateReport){
                    event.participants.each {
                        new EventProgress(eventId: event.id,user: it).save()
                    }
                }
                systemLogService.saveLog(SystemLogService.LogType.EVENT,SystemLogService.LogAction.CREATE,event.author,
                        event.id,event.title)
                if(params.sendSms){
                    eventNotifyJobService.sendEventSms(event,"创建");
                }
                log.info("params.isNotify--->"+params.isNotify)
                if(params.isNotify){
                    def notify = saveEventNotify(params,event)
                    eventNotifyJobService.addEventNotify(notify)
                }
                return event
            }else{
                event.errors.each {
                    log.info it
                }
            }
        } catch (ValidationException e) {
            log.error(e.message)
        }
        return null
    }

    private EventNotify saveEventNotify(params,Event event){
        def notifyType = EventNotifyType.valueOf(params.eventNotifyType)
        def notify = new EventNotify(notifyValue: genNotifyValueJson(params,notifyType),eventNotifyType:notifyType,event: event)
        if(!notify.save()){
            notify.errors.each {
                log.warn(it)
            }
        }
        return notify
    }
    private String genNotifyValueJson(params,EventNotifyType notifyType){
        def notifyValue = [:]
        switch (notifyType){
            case(EventNotifyType.EVERY_DAY):
                notifyValue.put("hour",params.hour)
                notifyValue.put("minute",params.minute)
                break;
            case(EventNotifyType.ONCE):
                notifyValue.put("time",params.notifyTime)
                break;
            case(EventNotifyType.EVERY_WEEK):
                notifyValue.put("hour",params.hour)
                notifyValue.put("minute",params.minute)
                notifyValue.put("week",params.week)
                break;
        }
        return JSON.toJSONString(notifyValue)
    }
    private EventNotify updateEventNotify(params,EventNotify oldEventNotify){
        def notifyType = EventNotifyType.valueOf(params.eventNotifyType)
        def notifyValueJson = genNotifyValueJson(params,notifyType)
        if(oldEventNotify.eventNotifyType==notifyType&&notifyValueJson==oldEventNotify.notifyValue){
             return  oldEventNotify
        }else{
            oldEventNotify.notifyValue =  notifyValueJson
            oldEventNotify.eventNotifyType =  notifyType
            return oldEventNotify.save()
        }
    }

    Event updateEvent(id, params){
        def eventInstance = Event.get(id)
        if (eventInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (eventInstance.version > version) {
                    eventInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                            ['Event'] as Object[], "Another user has updated this Event while you were editing")
                    log.error("Update Event: " + eventInstance.id + " was not successful, there are currency conflicts! ")
                    return null
                }
            }
            def oldSeparateReport = eventInstance.separateReport
            eventInstance.properties = params
            if(oldSeparateReport==true&&eventInstance.separateReport==false){
                EventProgress.executeUpdate("delete from EventProgress where eventId=?",[eventInstance.id])
            }
            if(eventInstance.separateReport==true){
                def oldEventProgresses = EventProgress.executeQuery("select e.id,u.id from EventProgress e join e.user u where e.eventId=?",eventInstance.id)
                def mapTemp = [:]
                oldEventProgresses.each {
                    mapTemp.put(it[1],it[0])
                }
                eventInstance.participants.each {
                    if(mapTemp.get(it.id)==null){
                        new EventProgress( eventId: eventInstance.id,user: it).save()
                    }else{
                        mapTemp.remove(it.id)
                    }
                }
                mapTemp.each {key,value->
                    EventProgress.executeUpdate("delete from EventProgress where id=?",[value])
                }
            }
            if(params.isNotify){
                def eventNotify = EventNotify.findByEvent(eventInstance)
                if(eventNotify){
                    def notify = updateEventNotify(params,eventNotify)
                    eventNotifyJobService.updateNotify(notify)
                }else{
                    def notify = saveEventNotify(params,eventInstance)
                    eventNotifyJobService.addEventNotify(notify)
                }
            }
            if (!eventInstance.hasErrors() && eventInstance.save(flush: true)) {
                systemLogService.saveLog(SystemLogService.LogType.EVENT,SystemLogService.LogAction.UPDATE,eventInstance.author,
                        eventInstance.id,eventInstance.title)
                if(params.sendSms){
                    eventNotifyJobService.sendEventSms(eventInstance,"更新");
                }
                return eventInstance
            }else{
                log.error("Can not Update Event: " + eventInstance.id)
                return null
            }
        }
        return null
    }


    public genCurrentEventsByUser(User user){
        Long userId = (Long)(SecurityUtils.subject.getPrincipal())
        def query = Event.where{
            (participants { id == user.id })&&progress<100
        }
        def events = query.list()
        return events
    }

    def delete(Event event){
        def result = [:]
        if(event){
            EventProgress.executeUpdate("delete from EventProgress where eventId=?",[event.id])
            def eventNotify = EventNotify.findByEvent(event)
            if(eventNotify){
                eventNotifyJobService.deleteNotify(eventNotify)
                eventNotify.delete()
            }
            event.delete()
            systemLogService.saveLog(SystemLogService.LogType.EVENT,SystemLogService.LogAction.DELETE,event.author,
                    event.id,event.title)
            result.put(StaticField.SUCCESS,true)
        }else{
            result.put(StaticField.SUCCESS,false)
            result.put(StaticField.MSG,"没有找到相关任务")
        }
        return result
    }

    def getCalEvent(Event event){
        if(null==event){
            return null
        }
        def masterUserFullName=  event.masterUser.fullName
        def result =  [ id: event.id,
                title: event.title,
                start: event.startDate,
                end: event.endDate,
                description: event.description==null?"":event.description,
                allDay: false,
                author:event.author.fullName,
                resource: event.participants?.collect{it.id},
                participants: event.participants?.collect{it.fullName},
                eventLevel:event.eventLevel,
                masterUserFullName:masterUserFullName
              ]
        switch (event.eventLevel){
            case(1) :
                result.put("backgroundColor","#D71319")
                break;
            case(2) :
                result.put("backgroundColor","#D83615")
                break;
            case(3) :
                result.put("backgroundColor","#DB7C12")
                break;
            case(4) :
                result.put("backgroundColor","#DCBE1A")
                break;
            default:result.put("backgroundColor","#DCBE1A")
        }
        result.put("textColor","#FFFFFF")
        return  result
    }


    def searchResult(params,HttpSession session){
        def hql = "select distinct e from  Event e join e.eventType eType " +
                "left join e.masterUser m left join e.participants p left join p.roles r where 1=1 "
        def searchParams = [:]
        if(session.getAttribute(StaticField.SESSION_BUILD_ID)!=null){
            hql = hql + " and e.buildProject.id=:bProjectId "
            searchParams.put("bProjectId",session.getAttribute(StaticField.SESSION_BUILD_ID))
        }
        if(params.eventId){
            def eventId = params.eventId as Long
            hql = hql + " and e.id=:eId "
            searchParams.put("eId",eventId)
        }
        if(params.userId){
            if(params.userId.toString().startsWith("role")){
                def roleId = params.userId.split("-")[1] as Long
                hql = hql + " and r.id=:roleId "
                searchParams.put("roleId",roleId )
            }else{
                def userId = params.userId as Long
                if(userId!=0){
                    hql = hql + " and (p.id=:userId  or m.id=:userId) "
                    searchParams.put("userId",userId )
                    params.sort = " m.fullName, e.startDate"
                    params.order = "desc"
                }
            }
        }
        if(params.selectCondition){
            def condition=params.selectCondition.toString();
            switch (condition) {
                case("noCondition"):
                    break;
                case("hideFinish"):
                    hql = hql + " and e.progress<100 "
                    break;
                case("justFinish"):
                    hql = hql + " and e.progress=100 "
                    break;
                case("justDelay"):
                    hql = hql + " and e.progress<100 and e.endDate<:nowDate"
                    searchParams.put("nowDate",new Date())
                    break;
                default:
                    break;
            }

        }
        def  eventTypeId =  params.eventTypeId as Long
        if(eventTypeId>0){
            hql = hql + " and eType.id=:eTypeId "
            searchParams.put("eTypeId",eventTypeId)
        }
        def  eventLevel =  params.eventLevel as Short
        if(eventLevel>0){
            hql = hql + " and e.eventLevel=:eventLevel "
            searchParams.put("eventLevel",eventLevel)
        }
        if(params.searchStr){
            if(params.searchStr.toString().trim()!=""){
                params.searchStr =   "%" + params.searchStr + "%"
                hql = hql + " and (e.title like :searchStr or e.description like :searchStr) "
                searchParams.put("searchStr",params.searchStr)
            }
        }
        if(params.startDate){
            try{
                def startDate = StaticMethod.parseDate(params.startDate.toString().trim())
                hql = hql + " and e.startDate >:startDate"
                searchParams.put("startDate",startDate)
            }catch(e){
                log.error("format startTime error:"+params.startDate)

            }

        }
        if(params.endDate){
            try{
                def endDate = StaticMethod.parseDate(params.endDate.toString().trim())
                hql = hql + " and e.endDate <:endDate"
                searchParams.put("endDate",endDate)
            }catch(e){
                log.error("format endDate error:"+params.endDate)
            }
        }
        log.info "hql----->"+ hql
        def countHql = "select count(distinct e) "+hql.substring(hql.indexOf("from"))
        //genResultMap(params,Class classInstance,String hql,String countHql,Map queryMap)
        return ajaxPaginateService.genResultMap(params,Event.class,hql,countHql, searchParams)
    }

    def updateProgress(Long id,Short progress){
        def  event = Event.get( id)
        event.progress = progress
        if(event.progress==100){
            event.finishDate = new Date()
        }else if(event.finishDate!=null){
            event.finishDate = null
        }
        String result = ""
        if (!event.save(flush: true)) {
            result="更新失败!!"
        }
        else{
            String dairyContent =  "完成任务"+event.title + " 任务id："+event.id
            userLogService.saveEventLog(dairyContent,event.progress,event.masterUser)
            result =  "    修改成功" + event.progress +"%"
        }
        return result
    }
    def updateSeparateProgress(Long eventProgressId,short progress){
        def  eventProgress = EventProgress.get(eventProgressId)
        eventProgress.progress = progress
        if(eventProgress.progress==100){
            eventProgress.finishDate = new Date()
        }else if(eventProgress.finishDate!=null){
            eventProgress.finishDate = null
        }
        String result = ""
        if (!eventProgress.save(flush: true)) {
            result="更新失败!!"
        }
        else{
            def event = Event.get(eventProgress.eventId)
            if(event){
                String dairyContent =  "完成任务"+event.title + " 任务id："+event.id
                userLogService.saveEventLog(dairyContent,event.progress,event.masterUser)
            }
            result =  "    修改成功" + eventProgress.progress +"%"
        }
        return result
    }
}
