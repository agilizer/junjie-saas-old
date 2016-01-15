define([
    'angular',
    'angular-couch-potato',
    'angular-ui-router',
    'angular-google-plus',
    'angular-easyfb'
], function (ng, couchPotato) {

    "use strict";

    var module = ng.module('app.auth', [
        'ui.router'
    ]);

    couchPotato.configureApp(module);


    module.config(function ($stateProvider, $couchPotatoProvider

        ) {
        $stateProvider.state('forgotPassword', {
            url: '/forgot-password',
            views: {
                root: {
                    templateUrl: 'app/auth/views/forgot-password.html'
                }
            },
            data: {
                title: 'Forgot Password',
                htmlId: 'extr-page'
            },
            resolve: {
                deps: $couchPotatoProvider.resolveDependencies([
                    'modules/forms/directives/validate/smartValidateForm'
                ])
            }
        })

        .state('lock', {
            url: '/lock',
            views: {
                root: {
                    templateUrl: 'app/auth/views/lock.html'
                }
            },
            data: {
                title: 'Locked Screen',
                htmlId: 'lock-page'
            }
        })


    });

    module.run(function($couchPotato){
        module.lazy = $couchPotato;
    });
    return module;
});