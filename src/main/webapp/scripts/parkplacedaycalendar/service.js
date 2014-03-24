'use strict';

parkingfriendsApp.factory('ParkPlaceDayCalendar', ['$resource',
    function ($resource) {
        return $resource('app/rest/parkplacedaycalendars/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': { method: 'GET'}
        });
    }]);
