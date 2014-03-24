'use strict';

parkingfriendsApp.factory('ParkPlaceGroup', ['$resource',
    function ($resource) {
        return $resource('app/rest/parkplacegroups/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': { method: 'GET'}
        });
    }]);
