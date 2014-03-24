'use strict';

parkingfriendsApp
    .config(['$routeProvider', '$httpProvider', '$translateProvider',
        function ($routeProvider, $httpProvider, $translateProvider) {
            $routeProvider
                .when('/parkplacedaycalendar', {
                    templateUrl: 'views/parkplacedaycalendars.html',
                    controller: 'ParkPlaceDayCalendarController',
                    resolve:{
                        resolvedParkPlaceDayCalendar: ['ParkPlaceDayCalendar', function (ParkPlaceDayCalendar) {
                            return ParkPlaceDayCalendar.query();
                        }]
                    }
                })
        }]);
