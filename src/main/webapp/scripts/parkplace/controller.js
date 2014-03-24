'use strict';

parkingfriendsApp.controller('ParkPlaceController', ['$scope', 'resolvedParkPlace', 'ParkPlace',
    function ($scope, resolvedParkPlace, ParkPlace) {

        $scope.parkplaces = resolvedParkPlace;

        $scope.create = function () {
            ParkPlace.save($scope.parkplace,
                function () {
                    $scope.parkplaces = ParkPlace.query();
                    $('#saveParkPlaceModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            $scope.parkplace = ParkPlace.get({id: id});
            $('#saveParkPlaceModal').modal('show');
        };

        $scope.delete = function (id) {
            ParkPlace.delete({id: id},
                function () {
                    $scope.parkplaces = ParkPlace.query();
                });
        };

        $scope.clear = function () {
            $scope.parkplace = {id: "", sampleTextAttribute: "", sampleDateAttribute: ""};
        };
    }]);
