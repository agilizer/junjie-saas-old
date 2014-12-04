define(['modules/forms/module', 'bootstrap-select'], function (module) {

    'use strict';

    module.registerDirective('smartSelect2', function () {
        return {
            restrict: 'A',
            compile: function (element, attributes) {
                element.removeAttr('smart-select2 data-smart-select2');
                element.selectpicker();
            }
        }
    });
});
