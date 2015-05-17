/**
 * Created by bartosz on 17/05/15.
 */
describe("RegisterService", function() {

    var registerService;
    var $httpBackend;

    beforeEach(module('app'));
    beforeEach(inject(function(_registerService_, _$httpBackend_) {
        registerService = _registerService_;
        $httpBackend = _$httpBackend_;
    }));

    describe("construction", function() {

        it ("should properly expose behaviour" ,function() {
            expect(registerService).to.have.property('registerNewUser');
        });

    });


    describe("registerNewUser", function() {

        it("should delegate to http service", function() {

            var data = {};

            $httpBackend.expectPOST('/register',data).respond(201,{});
            registerService.registerNewUser(data,function(){}, function(){});
            $httpBackend.flush();

        });

    })

});