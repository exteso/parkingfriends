'use strict';

parkingfriendsApp
    .config(['$routeProvider', '$httpProvider', '$translateProvider',
        function ($routeProvider, $httpProvider, $translateProvider) {
            $routeProvider
                .when('/parkplacegroup', {
                    templateUrl: 'views/parkplacegroups.html',
                    controller: 'ParkPlaceGroupController',
                    resolve:{
                        resolvedParkPlaceGroup: ['ParkPlaceGroup', function (ParkPlaceGroup) {
                            return ParkPlaceGroup.query();
                        }]
                    }
                })
        }]);
