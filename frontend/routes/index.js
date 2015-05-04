var User = require('../model/user.js');
var config = require('../config');
var http = require('http');

var index = function (req, res) {
    res.render('index');
};


var view = function (req, res) {
    var view = req.params.view;
    res.render(view);
};

var loginGET = function (req, res) {
    res.render('login');
};

var loginPOST = function (req, res) {
    User.findOne({ user_name: req.body.user_name}, function (err, user) {
        if (user == null) {
            res.status(401).send('User with a provided user name does not exist.');
        }
        else {
            if (req.body.hashed_password === user.hashed_password) {
                req.session.user_id = user.id;
                res.send(201);
            } else {
                res.status(401).send('Bad password');
            }
        }
    });
};

var logout = function (req, res) {
    delete req.session.user_id;
    res.send(201);
};

var registerGET = function (req, res) {
    res.render('register');
};

var registerPOST = function (req, res) {
    //check if user with given user_name exists
    User.findOne({ user_name: req.body.user_name}, function (err, user) {
        if (user == null) {
            var user = User(req.body);
            user.save();
            res.send(201);
        }

        else {
            res.status(409).send('User with a provided user name already exists.');
        }
    })
};

var loggedUserGET = function (req, res) {
    User.findById(req.session.user_id, function (err, user) {
        res.send(user);
        return;
    });
};


var newSearch = function (req, res) {

};



module.exports = {
    index: index,
    loginGET: loginGET,
    loginPOST: loginPOST,
    view: view,
    registerPOST: registerPOST,
    registerGET: registerGET,
    loggedUserGET: loggedUserGET,
    logout: logout
};
