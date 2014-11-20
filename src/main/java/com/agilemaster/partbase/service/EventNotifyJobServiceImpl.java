package com.agilemaster.partbase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agilemaster.partbase.entity.Event;
import com.agilemaster.partbase.entity.EventNotify;
import com.agilemaster.partbase.entity.User;
import com.junjie.commons.db.client.DataSourceSelecter;
import com.junjie.commons.sms.SmsService;
import com.junjie.commons.utils.DateUtil;
import com.junjie.commons.utils.JunjieConstants;
import com.junjie.commons.utils.JunjieStaticMethod;

@Service
public class EventNotifyJobServiceImpl implements EventNotifyJobService{

	@Autowired
	ConfigDomainService configDomainService;
	@Autowired
    SmsService	smsService;
	@Autowired
	ShareService shareService;
    @Autowired
	DataSourceSelecter dataSourceSelecter;
	
	@Override
	public void sendEventSms(Event event, String action) {
		String content = "";
        String template =  configDomainService.getConfigString(JunjieConstants.SMS_TEMPLATE_EVENT);
        template = template.replaceAll(JunjieConstants.smsTemplateVar.SMS_T_USERNAME.getValue(),event.getAuthor().getFullName());
        template = template.replaceAll(JunjieConstants.smsTemplateVar.SMS_T_START_DATE.getValue(),DateUtil.format(event.getStartDate(),"yyyy-MM-dd"));
        template = template.replaceAll(JunjieConstants.smsTemplateVar.SMS_T_END_DATE.getValue(),DateUtil.format(event.getEndDate(),"yyyy-MM-dd"));
        template = template.replaceAll(JunjieConstants.smsTemplateVar.SMS_T_TITLE.getValue(),JunjieStaticMethod.lenECString(event.getTitle(),12));
        template = template.replaceAll(JunjieConstants.smsTemplateVar.SMS_T_ACTION.getValue(),action);
        template = template.replaceAll(JunjieConstants.smsTemplateVar.SMS_T_URL.getValue(),configDomainService.getConfigString(JunjieConstants.SERVER_URL)
                +SMS_URL_PATH+"/"+dataSourceSelecter.getCurrentDataSourceKey()+"/"+event.getId());
        for(User user:event.getParticipants()){
        	 content =  template.replaceAll(JunjieConstants.smsTemplateVar.SMS_T_RECEIVE_USERNAME.getValue(),user.getFullName());
 		     smsService.sendSMS(user.getUsername(),content);
        }
        User user = event.getMasterUser();
        if(user!=null){
        	 content =  template.replaceAll(JunjieConstants.smsTemplateVar.SMS_T_RECEIVE_USERNAME.getValue(),user.getFullName());
 		     smsService.sendSMS(user.getUsername(),content);
        }
	}

	@Override
	public void addEventNotify(EventNotify notify) {
		// TODO Auto-generated method stub
		
	}

}
