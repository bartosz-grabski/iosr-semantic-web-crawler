services.factory('searchService', function ($http) {

    var service = {};

    service.newSearch = function (data, onSuccess, onFailure) {
        onSuccess();
    };

    
    service.getSearchResults = function(searchId, onSuccess, onFailure) {

    };


    return service;

});