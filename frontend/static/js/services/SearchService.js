services.factory('searchService', function ($http, $q, loginService) {

	var searchNodesUrls = [
		"http://localhost:8080",
		"http://localhost:8081"
	]

    var service = {};

    service.putQuery = function (data, userId, onSuccess, onFailure) {

    	console.log("Creating new search with data: "+data);
        
        var deferred = $q.defer();
        var promise = deferred.promise;
        var counter = 0;

        var url = searchNodesUrls[counter] + "/queries/"+userId

        function putSearch() {
        	$http.put(url,{
        		query_content: data,
        		crawling_interval: 10,
        		accuracy_cap: 0.7
        	})
        	.success(function(data) {
        		deferred.resolve(data);
        	}).error(function() {
        		counter++;
        		if (counter < searchNodesUrls.length) {
        			putSearch()
        		} else {
        			deferred.reject();
        		}
        	})

        }

        putSearch();

        promise.then(onSuccess,onFailure);

    };

    
    service.getQueries = function(userId, onSuccess, onFailure) {

    	console.log("Getting search results");



    };

    service.getQueryDetails = function() {

    	$http.get()

    };


    return service;

});