// Defer AngularJS bootstrap
window.name = "NG_DEFER_BOOTSTRAP!";

define([
    'require',
    'jquery',
    'angular',
    'domReady',
    'init',
    //'pace',
    'bootstrap',
    'appConfig',
    'app',
    'includes' ,
    "my97DatePicker"
], function (require, $, ng, domReady) {
    'use strict';
    console.log("Defer AngularJS bootstrap--------------------");
    $.sound_path = appConfig.sound_path;
    $.sound_on = appConfig.sound_on;


    domReady(function (document) {   	
        ng.bootstrap(document, ['app']);
        ng.resumeBootstrap();
    });
});
