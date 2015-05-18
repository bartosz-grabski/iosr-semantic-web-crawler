/**
 * Created by bartosz on 17/05/15.
 */
describe("SearchService", function() {

    var searchService;
    var $httpBackend;
    var queryPath="/queries/";

    beforeEach(module('app'));
    beforeEach(inject(function(_searchService_, _$httpBackend_) {
        searchService = _searchService_;
        $httpBackend = _$httpBackend_;
    }));

    describe("construction", function() {

        it ("should properly expose behaviour" ,function() {
            expect(searchService).to.have.property('postQuery');
            expect(searchService).to.have.property('getAllQueriesForUser');
            expect(searchService).to.have.property('getQueryDetails');
            expect(searchService).to.have.property('getNodesUrls');
        });

        it ("should set nodes' urls", function() {
            var urls = searchService.getNodesUrls();
            assert.equal(2,urls.length) //currently only two static nodes
        })
    });


    describe("methods", function() {

        it ("should properly delegate to $http service", function() {

            var userId = 'user';
            var queries = [];
            var query_id = '';

            var onSuccess = function(res){
                queries = res;
            };
            var onFailure = function(){};
            var urls = searchService.getNodesUrls();

            $httpBackend.expectGET(urls[0]+queryPath+userId).respond(200, [1,2,3]);
            searchService.getAllQueriesForUser(userId,onSuccess,onFailure);
            $httpBackend.flush();

            assert.equal(3,queries.length);


            onSuccess = function(res) {
                query_id = res.query_id;
            };

            $httpBackend.expectPOST(urls[0]+queryPath,{
                user_id : 'user',
                query_content: 'query',
                crawling_interval : 3600,
                accuracy_cap : 0.7
            }).respond(201, { query_id : 'id'})
            searchService.postQuery('query', 3600, 70, 'user', onSuccess, onFailure);
            $httpBackend.flush();

            assert.equal('id',query_id);

            var query_id = 100;
            var queryDetails;

            onSuccess = function(res) {
                queryDetails = res;
            };

            $httpBackend.expectGET(urls[0]+queryPath+query_id).respond(200, "response");
            searchService.getQueryDetails(query_id,onSuccess,onFailure);
            $httpBackend.flush();

            assert.equal("response", queryDetails);
        });

        it ("should retry on next url", function() {

            var query_id = 100;
            var queryDetails;

            var onSuccess = function(res) {
                queryDetails = res;
            };

            var onFailure = function() {

            };

            var urls = searchService.getNodesUrls();

            $httpBackend.expectGET(urls[0]+queryPath+query_id).respond(404);
            $httpBackend.expectGET(urls[1]+queryPath+query_id).respond(200, "response");
            searchService.getQueryDetails(query_id,onSuccess,onFailure);
            $httpBackend.flush();

            assert.equal("response", queryDetails);

        });

    })

});