define(['angular',
    'angular-couch-potato',
    'angular-ui-router'], function (ng, couchPotato) {

    "use strict";


    var module = ng.module('app.layout', ['ui.router']);


    couchPotato.configureApp(module);

    module.config(function ($stateProvider, $couchPotatoProvider, $urlRouterProvider) {


        $stateProvider
            .state('app', {
                abstract: true,
                views: {
                    root: {
                        templateUrl: 'app/layout/layout.tpl.html',
                        resolve: {
                            deps: $couchPotatoProvider.resolveDependencies([
                            ])
                        }
                    }
                }
            });
        if(isAuth == true){
        	$urlRouterProvider.otherwise('/dashboard');
        }else{
        	window.location.href=cloudUrl;
        }
    });

    module.run(function ($couchPotato) {
        module.lazy = $couchPotato;
    });

    return module;

});
