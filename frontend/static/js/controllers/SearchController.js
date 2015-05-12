controllers.controller('SearchController', function ($scope, $location, $routeParams, $interval, searchService) {

	var messages = {
		"errorSubmittingSearch": "Could not reach successfully any of the search nodes",
		"successSubmittingSearch": "Successfully submitted search"
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
			query_id : 1,
		},
		{
			query_content : "Mechanik kraków",
			query_processed : "",
			status : "finished",
			query_id : 2,
		}
	];

	$scope.searchId = $routeParams.searchId;
	$scope.error = false;
	$scope.success = false;
	$scope.message = "";
	//http://odetocode.com/blogs/scott/archive/2013/07/16/angularjs-listening-for-destroy.aspx

	$scope.createSearch = function(query) {

		var onSuccess = function(query_id) {
			$scope.success = true;
			$scope.error = false;
			$scope.message = messages["successSubmittingSearch"];
			$scope.searches.push( {
				query_content: query,
				status: "submitted",
				query_id : query_id,
				query_processed: ""
			})

		}

		var onFailure = function(query_id) {
			$scope.success = false;
			$scope.error = true;
			$scope.message = messages["errorSubmittingSearch"];
			$scope.searches.push( {
				query_content: query,
				status: "failed",
				query_id : query_id,
				query_processed: ""
			});

			//todo
			
		}

		searchService.putQuery(query, $scope.currentUser, onSuccess,onFailure);

	}

	$scope.loadSearches = function() {

		var onSuccess = function(values) {
			value = [{

			}];
			$scope.concat
		}

		var onFailure = function() {

		}

		searchService.getQueries($scope.currentUser);
	};

	$scope.startTimedUpdates = function() {
		console.log('Starting timed updates');
		$scope.updating = true;
		stop = $interval(function() {
			console.log('updating');
			
			var onSuccess = function() {

			};

			var onFailure = function() {

			};

			searchService.get

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

	$scope.loadSearches();

});