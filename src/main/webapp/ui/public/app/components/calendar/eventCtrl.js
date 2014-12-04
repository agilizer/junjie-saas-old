define(['components/calendar/module'], function (module) {

    'use strict';

    // alert("qwe");

    module.registerController('eventCreateCtrl', function ($scope,$modalInstance,$log) {

        $scope.ok = function () {
            $modalInstance.close($scope.selected.item);
          };

          $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
          };

    });
});
