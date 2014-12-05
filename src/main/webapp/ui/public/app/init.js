/**
 * Created by asdtiang on 14-12-2.
 */
var cloudUrl = "";
var junjieBaseUrl = "";
var isAuth = false;
var userInfo;
var JUNJIE_URL = {
    baseIndex:junjieBaseUrl+"/api/v1/index.do"
}
$.ajax({
    type: "post",
    url: JUNJIE_URL.baseIndex,
    dataType: "json",
    async:false,
    success: function(result){
    	cloudUrl = result.cloudUrl;
    	isAuth = result.success;
    	if(isAuth == true){
	   userInfo = result.data;
    	}else{
    		console.log("not login!!!!!!!!!!!!!!!");
    		window.location.href=cloudUrl;
    	}
    }
});
