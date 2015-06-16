package pl.edu.agh.iosr;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Key;
import pl.edu.agh.iosr.model.MongoConnector;
import pl.edu.agh.iosr.model.Query;
import pl.edu.agh.iosr.model.QueryDAO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class QueryTest {
    private String objectSerialized = "{\"query_id\":null,\"owner_id\":\"ownerId\",\"query_content\":\"Looking for job\",\"processed_content\":null,\"crawling_interval\":360,\"accuracy_cap\":0.22,\"status\":null}";

    private Query query;

    private MongoConnector connector;

    public QueryTest() {
        query = new Query();
        query.setAccuracyCap(0.22);
        query.setCrawlingInterval(360);
        query.setOwnerId("ownerId");
        query.setQueryContent("Looking for job");
    }

    @Before
    public void before() {
        connector = new TestDatabaseConnector();
    }

//
//    @Test
//    public void testJsonSerialization() {
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            assertEquals(mapper.writeValueAsString(query), objectSerialized);
//        } catch (JsonProcessingException e) {
//            fail();
//        }
//    }

    @Test
    public void ifCanAddObjectInDatabase() {
        QueryDAO dao = new QueryDAO(connector.getClient(), connector.getMorphia(), connector.getDbName());
        Key<Query> key = dao.save(query);
        Query one = dao.get((ObjectId) key.getId());
        assertEquals(one.getAccuracyCap(), query.getAccuracyCap(), 0.01);
        assertEquals(one.getCrawlingInterval(), query.getCrawlingInterval());
        assertEquals(one.getOwnerId(), query.getOwnerId());
        assertEquals(one.getQueryContent(), query.getQueryContent());
        assertEquals(one.getQueryId(), query.getQueryId());
    }
}
