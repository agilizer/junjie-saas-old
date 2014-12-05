define(['components/calendar/module'], function (module) {

    'use strict';

    // alert("qwe");
    var  userList = {};
    var app = module.registerController('eventIndexCtrl', function ($scope,$log) {
         
    });
    app.filter('propsFilter', function() {
      return function(items, props) {
    var out = [];

    if (angular.isArray(items)) {
      items.forEach(function(item) {
        var itemMatches = false;

        var keys = Object.keys(props);
        for (var i = 0; i < keys.length; i++) {
          var prop = keys[i];
          var text = props[prop].toLowerCase();
          if (item[prop].toString().toLowerCase().indexOf(text) !== -1) {
            itemMatches = true;
            break;
          }
        }

        if (itemMatches) {
          out.push(item);
        }
      });
    } else {
      // Let the output be the input untouched
      out = items;
    }

    return out;
  };
});
    module.registerController('eventCreateCtrl', function ($scope,$modalInstance,$log,userService) {
          $scope.selectUser = {};
          $scope.selectUser = userService.getUserSelect();
          $scope.event = {};
          $scope.event.masterUserId = userService.getCurrentUser().id;
          $log.log("init select .....................");
          $log.log("selectUser"+$scope.selectUser);
          $log.log("event"+$scope.event);
          $scope.save = function () {
            $log.log("save" + $scope.event);
            $log.log($scope.event);
            $log.log("startDate--->value:"+$("#startDate").val());
          };
          $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
          };
          $scope.startDate = function () {
              WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,startDate:'%y-%M-01 00:00:00',el:'startDate'});
          };
    });
});
