package com.junjie.commons.utils;

public interface JunjieConstants {
	String SUCCESS = "success";
	String DATA = "data";
	String MSG = "msg";
	String ERROR_CODE = "errorCode";
	String DATA_SOURCE_KEY = "dataSourceKey";
	String USSER_ID = "userId";

	// error code start
	/**
	 * 资源没有找到
	 */
	int NOT_FOUND = 404;
	/**
	 * file not found
	 */
	int FILE_NOT_FOUND = 405;
	/**
	 * init db failed when register
	 */
	int INIT_DB_FAILED = 1;
	/**
	 * username not found when login
	 */
	int LOGIN_USERNAME_NOT_FOUND = 2;
	/**
	 * password wrong when login
	 */
	int LOGIN_PASSWORD_WRONG = 3;
	/**
	 * login unsuccess
	 */
	int LOGIN_OTHERS_ERROR = 4;

	int REGISTER_EXIST = 5;

	int REGISTER_NULL_USERNAME = 15;

	int REGISTER_COPMANY_EXIST = 8;

	int REG_PWD_WRONG = 6;

	int REG_PWD_NOT_EQUALS = 7;

	int ERROR_SERVER = 10;

	int COMPANY_ADD_CLOUD_ERROR = 11;

	int NOT_LOGIN = 12;

	// error code end
	// config domain start
	String SMS_TEMPLATE_EVENT = "smsTemplateEvent";
	String SERVER_URL = "serverUrl";
	String PHOTO_TYPE_STR = "photoType";
	String DAIRY_BUFFER_DAY = "dairyBufferDay";
	String OFFICE_START_TIME = "officeStartTime";
	String OFFICE_END_TIME = "officeEndTime";
    Long ONE_DATE_LONG = (long) (24*3600*1000);
	Long TEN_MINUTE_LONG = (long) (10*1000*60);
	int EVENT_NOTIFY_ONCE = 1;
	int EVENT_NOTIFY_EVERY_DAY = 2;
	/**
	 * use for quartz store EventNotifyBean key.
	 */
    String EVENT_NOTIFY_BEAN = "eventNotifyBean";
	static enum smsTemplateVar {
		SMS_T_USERNAME("#用户#"), SMS_T_START_DATE("#开始时间#"), SMS_T_END_DATE(
				"#结束时间#"), SMS_T_RECEIVE_USERNAME("#接收用户#"), SMS_T_TITLE("#标题#"), SMS_T_URL(
				"#任务链接#"), SMS_T_ACTION("#创建/更新/提醒#");
		String value;

		smsTemplateVar(String valueInput) {
			this.value = valueInput;
		}

		public String getValue() {
			return value;
		}
	}
	// config domain end
}
