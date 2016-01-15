define(['app'], function(app) {
	"use strict";

	return app.factory('userService', function($http, junjieUrl) {
		var userSelectList = "";
		function getUserSelect() {
			if (userSelectList == "") {
				$.ajax({
					type: "post",
					url: junjieUrl.userSelect,
					dataType: "json",
					async: false,
					success: function(result) {
						userSelectList = result
					}
				});
			}
			return userSelectList;
		}
		function getCurrentUser() {
			return userInfo;
		}


		return {
			getUserSelect: getUserSelect,
			getCurrentUser:getCurrentUser
		}

	})
})