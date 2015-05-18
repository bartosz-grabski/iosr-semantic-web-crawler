var express = require('express');
var routes = require('./routes');
var http = require('http');
var path = require('path');
var config = require('./config');
var mongoose = require('mongoose');


var app = express();

// all environments
//app.use(express.compress());
app.set('port', process.env.PORT || 3000);
app.set('views', path.join(__dirname, 'views'));
app.engine('.html', require('ejs').renderFile);
app.set('view engine', 'html');
app.use(express.logger('dev'));
app.use(express.static(path.join(__dirname, 'static')));
app.use(express.bodyParser());
app.use(express.methodOverride());
app.use(express.cookieParser());
app.use(express.session({secret: '1234567890QWERTY'}));
app.use(app.router);


mongoose.connect('mongodb://' + config["dbHost"] + ":" + config["dbPort"] + "/" + config["dbDatabase"]);

// development only
if ('development' == app.get('env')) {
    app.use(express.errorHandler());
}

http.createServer(app).listen(app.get('port'), function () {
    console.log('Express server listening on port ' + app.get('port'));
});

app.get('/',routes.index)
app.get('/home', routes.index);
app.get('/register', routes.registerGET);
app.post('/register', routes.registerPOST);
app.get('/login', routes.loginGET);
app.post('/login', routes.loginPOST);
app.get('/views/:view', routes.view);
app.get('/loggedUser', routes.loggedUserGET);
app.get('/logout', routes.logout);
