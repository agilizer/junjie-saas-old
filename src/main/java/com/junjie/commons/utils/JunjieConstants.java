package com.junjie.commons.utils;

public interface JunjieConstants {
    String SUCCESS = "success";
    String DATA = "data";
    String MSG = "msg";
    String ERROR_CODE = "errorCode";
    String DATA_SOURCE_KEY = "dataSourceKey";
    
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
    int LOGIN_USERNAME_NOT_FOUND=2;
    /**
     * password wrong when login
     */
    int LOGIN_PASSWORD_WRONG=3;
    /**
     * login unsuccess 
     */
    int LOGIN_OTHERS_ERROR=4;
    
    //error code end
}
