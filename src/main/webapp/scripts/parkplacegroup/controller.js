'use strict';

parkingfriendsApp.controller('ParkPlaceGroupController', ['$scope', 'resolvedParkPlaceGroup', 'ParkPlaceGroup',
    function ($scope, resolvedParkPlaceGroup, ParkPlaceGroup) {

        $scope.parkplacegroups = resolvedParkPlaceGroup;

        $scope.create = function () {
            ParkPlaceGroup.save($scope.parkplacegroup,
                function () {
                    $scope.parkplacegroups = ParkPlaceGroup.query();
                    $('#saveParkPlaceGroupModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            $scope.parkplacegroup = ParkPlaceGroup.get({id: id});
            $('#saveParkPlaceGroupModal').modal('show');
        };

        $scope.delete = function (id) {
            ParkPlaceGroup.delete({id: id},
                function () {
                    $scope.parkplacegroups = ParkPlaceGroup.query();
                });
        };

        $scope.clear = function () {
            $scope.parkplacegroup = {id: "", sampleTextAttribute: "", sampleDateAttribute: ""};
        };
    }]);
