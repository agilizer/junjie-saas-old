define(["angular","angular-couch-potato","angular-ui-router"],function(a,b){"use strict";var c=a.module("app.layout",["ui.router"]);return b.configureApp(c),c.config(["$stateProvider","$couchPotatoProvider","$urlRouterProvider",function(a,b,c){a.state("app",{"abstract":!0,views:{root:{templateUrl:"build/layout/layout.tpl.html",resolve:{deps:b.resolveDependencies([])}}}}),c.otherwise("/dashboard")}]),c.run(["$couchPotato",function(a){c.lazy=a}]),c});