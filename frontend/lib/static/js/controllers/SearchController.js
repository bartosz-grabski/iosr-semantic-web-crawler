controllers.controller('SearchController', function ($scope, $location, $interval, searchService) {

    var messages = {
        "errorSubmittingSearch": "Could not reach successfully any of the search nodes",
        "successSubmittingSearch": "Successfully submitted search",
        "illegalSubmittedValues": "Query must not be empty, accuracy must be a value between 0 and 100, interval must be a value between 0 and 2692000"
    };

    var stop; // interval promise
    var UPDATE_INTERVAL = 5000;

    /*<li href="#" class="list-group-item">Mechanik kraków<span class="label label-success search-status">Finished</span></li>
     li href="#" class="list-group-item">Dapibus ac facilisis in  tortor mauris condimentum nibh, ut fermentum massa...
     tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.
     <span class="label label-warning search-status">In progress</span></li>*/

    $scope.searches = [
        {
            query_content : "Dapibus ac facilisis in  tortor mauris condimentum nibh, ut fermentum massa... tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.",
            query_processed : "",
            status : "running",
            query_id : 1
        },
        {
            query_content : "Mechanik kraków",
            query_processed : "",
            status : "finished",
            query_id : 2
        }
    ];

    $scope.error = false;
    $scope.success = false;
    $scope.message = "";
    //http://odetocode.com/blogs/scott/archive/2013/07/16/angularjs-listening-for-destroy.aspx

    $scope.createSearch = function(query, accuracy, crawlingInterval) {

        var onSuccess = function(query_id) {

            if (!$scope.newSearchForm.$valid) {
                $scope.error = true;
                $scope.success = false;
                $scope.message = messages["illegalSubmittedValues"];
            } else {
                $scope.success = true;
                $scope.error = false;
                $scope.message = messages["successSubmittingSearch"];
                $scope.searches.push( {
                    query_content: query,
                    status: "submitted",
                    query_id : query_id,
                    query_processed: ""
                });
            }


        };

        var onFailure = function() {
            $scope.success = false;
            $scope.error = true;
            $scope.message = messages["errorSubmittingSearch"];
        };

        searchService.postQuery(query, crawlingInterval, accuracy / 100.0, $scope.currentUser, onSuccess,onFailure);

    };

    $scope.loadSearches = function() {

        var onSuccess = function(values) {
            $scope.searches = values;
        };

        var onFailure = function() {
            $scope.success = false;
            $scope.error = true;
            $scope.message = messages["errorSubmittingSearch"];

        };

        searchService.getAllQueriesForUser($scope.currentUser,onSuccess,onFailure);
    };



    if ($scope.currentUser) $scope.loadSearches();

});