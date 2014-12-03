define(["angular","angular-couch-potato","angular-ui-router","angular-google-plus","angular-easyfb"],function(a,b){"use strict";var c=a.module("app.auth",["ui.router"]);return b.configureApp(c),c.config(["$stateProvider","$couchPotatoProvider",function(a,b){a.state("login",{url:"/login",views:{controller:"loginCtrl",root:{templateUrl:"build/auth/views/login.html"},resolve:{deps:b.resolveDependencies(["auth/authCtrl"])}},data:{title:"登录",htmlId:"extr-page"},resolve:{deps:b.resolveDependencies(["modules/forms/directives/validate/smartValidateForm","auth/authCtrl"])}}).state("register",{url:"/register",views:{root:{templateUrl:"build/auth/views/register.html",controller:"registerCtrl",resolve:{deps:b.resolveDependencies(["auth/authCtrl"])}}},data:{title:"注册",htmlId:"extr-page"},resolve:{deps:b.resolveDependencies(["modules/forms/directives/validate/smartValidateForm"])}}).state("forgotPassword",{url:"/forgot-password",views:{root:{templateUrl:"build/auth/views/forgot-password.html"}},data:{title:"Forgot Password",htmlId:"extr-page"},resolve:{deps:b.resolveDependencies(["modules/forms/directives/validate/smartValidateForm"])}}).state("lock",{url:"/lock",views:{root:{templateUrl:"build/auth/views/lock.html"}},data:{title:"Locked Screen",htmlId:"lock-page"}})}]),c.run(["$couchPotato",function(a){c.lazy=a}]),c});