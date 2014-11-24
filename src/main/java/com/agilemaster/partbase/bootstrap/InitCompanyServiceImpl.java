package com.agilemaster.partbase.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agilemaster.partbase.dao.ConfigDomainDao;
import com.agilemaster.partbase.entity.ConfigDomain;
import com.agilemaster.partbase.service.ShareService;
import com.junjie.commons.utils.JunjieConstants;
import com.junjie.commons.utils.JunjieCounter;

@Service
public class InitCompanyServiceImpl implements InitCompanyService{

	@Autowired
	JunjieCounter junjieCounter;
	@Autowired
	ConfigDomainDao configDomainDao;
	@Autowired
	ShareService shareService;
	@Override
	public void initConfigDomain(String storeKey) {
		ConfigDomain configDomain  = new ConfigDomain();
		configDomain.setDescription("任务短信模板");
		configDomain.setEditable(true);
		configDomain.setId(junjieCounter.genUniqueLong(ConfigDomain.ID_NAME));
		configDomain.setMapName(JunjieConstants.SMS_TEMPLATE_EVENT);
		 String template=JunjieConstants.smsTemplateVar.SMS_T_RECEIVE_USERNAME.getValue()+","+"您好!"
		+JunjieConstants.smsTemplateVar.SMS_T_USERNAME.getValue()+JunjieConstants.smsTemplateVar.SMS_T_ACTION.getValue()+
		"任务:"+JunjieConstants.smsTemplateVar.SMS_T_TITLE.getValue()+JunjieConstants.smsTemplateVar.SMS_T_URL.getValue();
		configDomain.setMapValue(template);
		configDomain.setValueType(ConfigDomain.ValueType.String);
		configDomain.setVersion(1l);
		configDomainDao.save(storeKey, configDomain);
		
		configDomain  = new ConfigDomain();
		configDomain.setDescription("服务器地址(短信发送使用),不以/结束");
		configDomain.setEditable(true);
		configDomain.setId(junjieCounter.genUniqueLong(ConfigDomain.ID_NAME));
		configDomain.setMapName(JunjieConstants.SERVER_URL);
		configDomain.setMapValue(shareService.getPluginUrl());
		configDomain.setValueType(ConfigDomain.ValueType.String);
		configDomain.setVersion(1l);
		configDomainDao.save(storeKey, configDomain);
		
		configDomain  = new ConfigDomain();
		configDomain.setDescription("工作开始时间");
		configDomain.setEditable(true);
		configDomain.setId(junjieCounter.genUniqueLong(ConfigDomain.ID_NAME));
		configDomain.setMapName(JunjieConstants.OFFICE_START_TIME);
		configDomain.setMapValue(9+"");
		configDomain.setValueType(ConfigDomain.ValueType.Integer);
		configDomain.setVersion(1l);
		configDomainDao.save(storeKey, configDomain);
		
		
		
		configDomain  = new ConfigDomain();
		configDomain.setDescription("工作结束时间");
		configDomain.setEditable(true);
		configDomain.setId(junjieCounter.genUniqueLong(ConfigDomain.ID_NAME));
		configDomain.setMapName(JunjieConstants.OFFICE_END_TIME);
		configDomain.setMapValue(18+"");
		configDomain.setValueType(ConfigDomain.ValueType.Integer);
		configDomain.setVersion(1l);
		configDomainDao.save(storeKey, configDomain);
//		 String template=StaticField.smsTemplateVar.SMS_T_RECEIVE_USERNAME.getValue()+","+"您好!"
//		+StaticField.smsTemplateVar.SMS_T_USERNAME.getValue()+StaticField.smsTemplateVar.SMS_T_ACTION.getValue()+
//		"任务:"+StaticField.smsTemplateVar.SMS_T_TITLE.getValue()+StaticField.smsTemplateVar.SMS_T_URL.getValue()
//		            new ConfigDomain(mapName: StaticField.SMS_TEMPLATE_EVENT, mapValue: template,
//		                    valueType: ConfigDomain.ValueType.String, description: "任务短信模板",editable: true).save(flush: true)
		
	}

}
