define(['modules/forms/module', 'modules/forms/common',
    'jquery-maskedinput', 'jquery-validation'], function (module, formsCommon) {

    'use strict';

    return module.registerDirective('eventEditForm', function () {
        return {
            restrict: 'E',
            replace: true,
            templateUrl: 'app/components/calendar/directives/event-edit-form.tpl.html',
            scope: true,
            link: function (scope, form) {



            }
        }
    });
});
