controllers.controller('SearchResultController', function ($scope, $location, $interval, $routeParams, searchService) {


    var UPDATE_INTERVAL = 5000;

    var messages = {
        "failedToGetResults" : "Failed to fetch query details"
    }

    $scope.results = [
        {
            url: "www.google.pl",
            accuracy_cap : 0.7,
            crawling_interval : 3600*24
        }
    ];

    $scope.startTimedUpdates = function() {
        console.log('Starting timed updates');
        $scope.updating = true;
        stop = $interval(function() {
            console.log('updating');

            var onSuccess = function(results) {
                $scope.error = false;
                console.log("Successfully fetched query details");
                for (var i = 0; i < queries.length; i++) {
                    $scope.results = results;
                }
            };

            var onFailure = function() {
                console.log("Failed to fetch query details");
                $scope.error = true;
                $scope.success = false;
                $scope.message = messages.failedToGetResults;
            };

            var queryId = $routeParams.searchId;

            searchService.getQueryDetails(queryId,onSuccess,onFailure);

        }, UPDATE_INTERVAL)
    };

    $scope.stopTimedUpdates = function() {
        console.log('Stopping timed updates');
        if (angular.isDefined(stop)) {
            $interval.cancel(stop);
            $scope.updating = false;
            stop = undefined;
        }
    };

    function initSearchResults() {
        var onSuccess = function(results) {
            $scope.error = false;
            console.log("Successfully fetched query details");
            $scope.results = results;
        };

        var onFailure = function() {
            console.log("Failed to fetch query details");
            $scope.error = true;
            $scope.success = false;
            $scope.message = messages.failedToGetResults;
        };

        var queryId = $routeParams.searchId;

        searchService.getQueryDetails(queryId,onSuccess,onFailure);
    }

    initSearchResults();

});
