'use strict';

parkingfriendsApp.factory('ParkPlace', ['$resource',
    function ($resource) {
        return $resource('app/rest/parkplaces/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': { method: 'GET'}
        });
    }]);
