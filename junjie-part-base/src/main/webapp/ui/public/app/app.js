'use strict';

/**
 * @ngdoc overview
 * @name app [smartadminApp]
 * @description
 * # app [smartadminApp]
 *
 * Main module of the application.
 */

define([
    'angular',
    'angular-couch-potato',
    'angular-ui-router',
    'angular-animate',
    'angular-bootstrap',
    'smartwidgets',
    'notification',
], function (ng, couchPotato) {

    var app = ng.module('app', [
        'ngSanitize',

        'scs.couch-potato',
        'ngAnimate',
        'ui.router',
        'ui.bootstrap',
        // App
        'app.auth',
        'app.layout',
        'app.dashboard',
        'app.forms',
        'app.calendar',
        'app.widgets'

    ]);

    couchPotato.configureApp(app);

    app.config(function ($provide, $httpProvider) {



        // Intercept http calls.
        $provide.factory('ErrorHttpInterceptor', function ($q) {
            var errorCounter = 0;
            function notifyError(rejection){
                console.log(rejection);
                $.bigBox({
                    title: rejection.status + ' ' + rejection.statusText,
                    content: rejection.data,
                    color: "#C46A69",
                    icon: "fa fa-warning shake animated",
                    number: ++errorCounter,
                    timeout: 6000
                });
            }

            return {
                // On request failure
                requestError: function (rejection) {
                    // show notification
                    notifyError(rejection);

                    // Return the promise rejection.
                    return $q.reject(rejection);
                },

                // On response failure
                responseError: function (rejection) {
                    // show notification
                    notifyError(rejection);
                    // Return the promise rejection.
                    return $q.reject(rejection);
                }
            };
        });

        // Add the interceptor to the $httpProvider.
        $httpProvider.interceptors.push('ErrorHttpInterceptor');

    });

    app.run(function ($couchPotato, $rootScope, $state, $stateParams) {
        app.lazy = $couchPotato;
        $rootScope.$state = $state;
        $rootScope.$stateParams = $stateParams;
        // editableOptions.theme = 'bs3';
    });
    var cloudUrl = "";
    var junjieBaseUrl = "";
    var JUNJIE_URL = {
        baseIndex:junjieBaseUrl+"/api/v1/index.do"
    }
    app.constant('junjieUrl',{
        baseIndex:junjieBaseUrl+"/api/v1/index.do",
        userSelect: junjieBaseUrl+"/api/v1/user/listSelect.do",
        eventSaveUrl:junjieBaseUrl+"/api/v1/event/create.do"
    });   
    

    return app;
});
