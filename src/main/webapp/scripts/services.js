'use strict';

/* Services */

ParkingFriendsApp.factory('Account', ['$resource',
    function ($resource) {
        return $resource('app/rest/account', {}, {
        });
    }]);

ParkingFriendsApp.factory('Password', ['$resource',
    function ($resource) {
        return $resource('app/rest/account/change_password', {}, {
        });
    }]);

ParkingFriendsApp.factory('Sessions', ['$resource',
    function ($resource) {
        return $resource('app/rest/account/sessions/:series', {}, {
            'get': { method: 'GET', isArray: true}
        });
    }]);

ParkingFriendsApp.factory('Metrics', ['$resource',
    function ($resource) {
        return $resource('/metrics/metrics', {}, {
            'get': { method: 'GET'}
        });
    }]);

ParkingFriendsApp.factory('LogsService', ['$resource',
    function ($resource) {
        return $resource('app/rest/logs', {}, {
            'findAll': { method: 'GET', isArray: true},
            'changeLevel':  { method: 'PUT'}
        });
    }]);

ParkingFriendsApp.factory('AuthenticationSharedService', ['$rootScope', '$http',
    function ($rootScope, $http) {
        return {
            message: '',
            prepForBroadcast: function(msg) {
                this.message = msg;
                this.broadcastItem();
            },
            broadcastItem: function() {
                $rootScope.$broadcast("authenticationEvent");
            },
            login: function (param) {
                var that = this;
                var data ="j_username=" + param.username +"&j_password=" + param.password +"&_spring_security_remember_me=" + param.rememberMe +"&submit=Login";
                $http.post('/app/authentication', data, {
                    headers: {
                        "Content-Type": "application/x-www-form-urlencoded"
                    }
                }).success(function (data, status, headers, config) {
                        $rootScope.authenticationError = false;
                        that.prepForBroadcast("login");
                        if(param.success){
                            param.success(data, status, headers, config);
                        }
                    }).error(function (data, status, headers, config) {
                        $rootScope.authenticationError = true;
                        if(param.error){
                            param.error(data, status, headers, config);
                        }
                    });
            },
            logout: function () {
                $rootScope.authenticationError = false;
                var that = this;
                $http.get('/app/logout')
                    .success(function (data, status, headers, config) {
                        that.prepForBroadcast("logout");
                    });
            }
        };
    }]);
