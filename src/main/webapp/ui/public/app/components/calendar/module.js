define(['angular',
    'angular-couch-potato',
    'angular-ui-router'], function (ng, couchPotato) {

    "use strict";


    var module = ng.module('app.calendar', ['ngResource','ui.router']);


    couchPotato.configureApp(module);

    module.config(function ($stateProvider, $couchPotatoProvider) {

        $stateProvider
            .state('app.calendar', {
                url: '/calendar',
                views: {
                    content: {
                        templateUrl: 'app/components/calendar/views/calendar.tpl.html'
                    }
                },
                data:{
                    title: '我的日历'
                },
                resolve: {
                    deps: $couchPotatoProvider.resolveDependencies([
                                'modules/widgets/directives/widgetGrid',
                                'modules/widgets/directives/jarvisWidget',
                                'components/calendar/eventCtrl',
                                'components/calendar/directives/smartSelect2'
                    ])
                }
            });
    });

    module.run(function ($couchPotato) {
        module.lazy = $couchPotato;
    });

    return module;

});
