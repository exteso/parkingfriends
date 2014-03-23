'use strict';

/* App Module */

var ParkingFriendsApp = angular.module('ParkingFriendsApp', ['ngResource', 'ngRoute', 'ngCookies', 'pascalprecht.translate']);

ParkingFriendsApp
    .config(['$routeProvider', '$httpProvider', '$translateProvider',
        function ($routeProvider, $httpProvider, $translateProvider) {
            $routeProvider
                .when('/login', {
                    templateUrl: 'views/login.html',
                    controller: 'LoginController'
                })
                .when('/settings', {
                    templateUrl: 'views/settings.html',
                    controller: 'SettingsController'
                })
                .when('/password', {
                    templateUrl: 'views/password.html',
                    controller: 'PasswordController'
                })
                .when('/sessions', {
                    templateUrl: 'views/sessions.html',
                    controller: 'SessionsController'
                })
                .when('/metrics', {
                    templateUrl: 'views/metrics.html',
                    controller: 'MetricsController'
                })
                .when('/logs', {
                    templateUrl: 'views/logs.html',
                    controller: 'LogsController'
                })
                .when('/logout', {
                    templateUrl: 'views/main.html',
                    controller: 'LogoutController'
                })
                .otherwise({
                    templateUrl: 'views/main.html',
                    controller: 'MainController'
                })

            // Handle the 401 error
            var unauthorizedInterceptor = ['$rootScope', '$q', '$location', function (scope, $q, $location) {
                function success(response) {
                    return response;
                }

                function error(response) {
                    var status = response.status;
                    if (status == 401) {
                        $location.path('/login').replace();
                    }
                    return $q.reject(response);
                }

                return function (promise) {
                    return promise.then(success, error);
                }
            }];
            $httpProvider.responseInterceptors.push(unauthorizedInterceptor);

            // Initialize angular-translate
            $translateProvider.useStaticFilesLoader({
                prefix: '/i18n/',
                suffix: '.json'
            });

            $translateProvider.preferredLanguage('en');

            // remember language
            $translateProvider.useCookieStorage();
        }]);
