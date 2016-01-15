define(['angular', 'angular-couch-potato','my97DatePicker',
    'angular-ui-router','angular-sanitize','angular-ui-select'], function (ng, couchPotato) {

    "use strict";


    var module = ng.module('app.calendar', ['ngResource','ui.router','ui.select']);


    couchPotato.configureApp(module);

    module.config(function ($stateProvider, $couchPotatoProvider) {

        $stateProvider
            .state('app.calendar', {
                url: '/calendar',
                views: {
                    content: {
                        controller:'eventIndexCtrl',
                        templateUrl: 'app/components/calendar/views/calendar.tpl.html'
                    },
                    resolve: {
                        deps: $couchPotatoProvider.resolveDependencies([
                                    'components/calendar/eventCtrl',
                                    'components/calendar/directives/eventEditForm'
                        ])
                    }
                },
                data:{
                    title: '我的日历'
                }
            });
    });

    module.run(function ($couchPotato) {
        module.lazy = $couchPotato;
    });

    return module;

});
