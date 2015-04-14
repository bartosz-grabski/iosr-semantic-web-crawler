var index = function (req, res) {
    res.render('index');
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

var logoutGET = function (req, res) {
    delete req.session.user_id;
    res.send(201);
};

module.exports = {
    index: index,
    loginGET: loginGET,
    loginPOST: loginPOST,
    logoutGET: logoutGET
};
