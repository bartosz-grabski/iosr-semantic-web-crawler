services.factory('searchService', function ($http, $q) {

    var searchNodesUrls = [
        "http://localhost:8080",
        "http://localhost:8081"
    ];

    var service = {};

    /**
     *
     * @param data
     * @param userId
     * @param onSuccess
     * @param onFailure
     */
    service.postQuery = function (query, crawling_interval, accuracy_cap, userId, onSuccess, onFailure) {

        console.log("Creating new search: "+ query + " " + crawling_interval + " " +accuracy_cap);

        var urls = searchNodesUrls.map(function(el) {
            return el+"/query";
        });

        var acc = accuracy_cap / 100;

        var data = {
            user_id: userId,
            query_content: query,
            crawling_interval: crawling_interval,
            accuracy_cap: acc
        };

        _performHttpRetryUrls($http.post, onSuccess, onFailure, urls, data)

    };

    /**
     * Gets all queries for a user
     * @param userId user to look queries up for
     * @param onSuccess on success callback
     * @param onFailure on failure callback
     * @param options additional options for query url (e.g status : running etc.)
     */
    service.getAllQueriesForUser = function(userId, onSuccess, onFailure, options) {

        console.log("Getting search results for user ");

        var urls = searchNodesUrls.map(function(el) {
            el = el + "/queries/"+userId;
            if (options) {
                el = el + "?"
                for (var o in options) {
                    el = el + o + "=" + options[o];
                }
            }
            return el;
        });

        _performHttpRetryUrls($http.get, onSuccess, onFailure, urls);


    };

    /**
     *
     * @param queryId
     * @param onSuccess
     * @param onFailure
     */
    service.getQueryDetails = function(queryId, onSuccess, onFailure) {


        console.log("Getting query details for query "+ queryId);

        var urls = searchNodesUrls.map(function(el) {
            el = el + "/query/" + queryId;
            return el;
        });

        _performHttpRetryUrls($http.get, onSuccess, onFailure, urls);

    };

    service.getNodesUrls = function() {
        return searchNodesUrls;
    };


    var _performHttpRetryUrls = function(httpOp, onSuccess, onFailure, urls, data){

        var counter = 0;
        var deferred = $q.defer();
        var promise = deferred.promise;

        function retryOp() {
            var url = urls[counter];
            httpOp(url, data)
                .success(function(res) {
                    deferred.resolve(res);
                })
                .error(function() {
                    counter++;
                    if (counter < urls.length) {
                        console.log("Retrying for another node");
                        retryOp();
                    } else {
                        deferred.reject();
                    }
                });
        }

        retryOp();

        promise.then(onSuccess,onFailure);

    };

    return service;

});