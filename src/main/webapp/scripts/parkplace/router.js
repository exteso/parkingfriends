'use strict';

parkingfriendsApp
    .config(['$routeProvider', '$httpProvider', '$translateProvider',
        function ($routeProvider, $httpProvider, $translateProvider) {
            $routeProvider
                .when('/parkplace', {
                    templateUrl: 'views/parkplaces.html',
                    controller: 'ParkPlaceController',
                    resolve:{
                        resolvedParkPlace: ['ParkPlace', function (ParkPlace) {
                            return ParkPlace.query();
                        }]
                    }
                })
        }]);
