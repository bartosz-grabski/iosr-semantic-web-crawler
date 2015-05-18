services.factory('loginService', function ($http) {

    var service = {};



    service.logIn = function (data, onSuccess, onFailure) {

        $http.post('/login', data)
            .success(function(res) {
                onSuccess(res);
                service.loggedInUser = res.user_name;
            })
            .error(onFailure);
    };

    service.logOut = function (onSuccess, onFailure) {
        $http.get('/logout').success(onSuccess).error(onFailure);
    };

    service.getLoggedInUser = function (onSuccess, onFailure) {
        $http.get('/loggedUser')
            .success(function(res) {
                onSuccess(res);
                service.loggedInUser = res.user_name;
            })
            .error(onFailure);
    }

    return service;
});