controllers.controller('ApplicationController', function ($scope, $location, loginService) {

    loginService.getLoggedInUser(function (res) {
        $scope.currentUser = res.user_name;
    }, function () {
        $scope.currentUser = null
    });


    $scope.setCurrentUser = function (user) {
        $scope.currentUser = user;
    };

    $scope.logout = function () {

        var onSuccess = function () {
            console.log("[INFO] Successful logout");
            $scope.currentUser = null;
            $location.path('/');
        };

        var onFailure = function () {
            console.log("[INFO] Logging out has failed")
        }

        loginService.logOut(onSuccess, onFailure);
    };

});