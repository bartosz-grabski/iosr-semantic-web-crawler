/**
 * Created by bartosz on 17/05/15.
 */
describe("LoginService", function() {

    var loginService;
    var $httpBackend;

    beforeEach(module('app'));
    beforeEach(inject(function(_loginService_, _$httpBackend_) {
        loginService = _loginService_;
        $httpBackend = _$httpBackend_;
    }));

    describe("construction", function() {

        it ("should properly expose behaviour" ,function() {
            expect(loginService).to.have.property('logIn');
            expect(loginService).to.have.property('logOut');
            expect(loginService).to.have.property('getLoggedInUser');
        });

    });


    describe("registerNewUser", function() {

        it("should delegate to http service", function() {

            var data = {};

            $httpBackend.expectPOST('/login',data).respond(201,{});
            loginService.logIn(data,function(){}, function(){});
            $httpBackend.flush();

            $httpBackend.expectGET('/loggedUser').respond(200,{});
            loginService.getLoggedInUser(function(){}, function(){});
            $httpBackend.flush();

            $httpBackend.expectGET('/logout').respond(200,{});
            loginService.logOut(function(){}, function(){});
            $httpBackend.flush();

        });

    })

});