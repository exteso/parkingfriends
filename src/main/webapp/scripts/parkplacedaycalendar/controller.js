'use strict';

parkingfriendsApp.controller('ParkPlaceDayCalendarController', ['$scope', 'resolvedParkPlaceDayCalendar', 'ParkPlaceDayCalendar',
    function ($scope, resolvedParkPlaceDayCalendar, ParkPlaceDayCalendar) {

        $scope.parkplacedaycalendars = resolvedParkPlaceDayCalendar;

        $scope.create = function () {
            ParkPlaceDayCalendar.save($scope.parkplacedaycalendar,
                function () {
                    $scope.parkplacedaycalendars = ParkPlaceDayCalendar.query();
                    $('#saveParkPlaceDayCalendarModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            $scope.parkplacedaycalendar = ParkPlaceDayCalendar.get({id: id});
            $('#saveParkPlaceDayCalendarModal').modal('show');
        };

        $scope.delete = function (id) {
            ParkPlaceDayCalendar.delete({id: id},
                function () {
                    $scope.parkplacedaycalendars = ParkPlaceDayCalendar.query();
                });
        };

        $scope.clear = function () {
            $scope.parkplacedaycalendar = {id: "", sampleTextAttribute: "", sampleDateAttribute: ""};
        };
    }]);
