package pl.edu.agh.iosr;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.*;
import pl.edu.agh.iosr.model.Query;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class QueryAPITest {

    private static TestServer SERVER = new TestServer();
    private static HttpServer server;
    private static WebTarget target;

    private String queryPath = "queries";

    private static String validResponse;

    private static Query query;

    @Before
    public void setUp() throws Exception {
        new RestApp(SERVER); //TODO Ugh so ugly, much not good. Lifecycle should be improved greatly
        server = SERVER.startServer();
        // create the client
        Client c = ClientBuilder.newClient();

        target = c.target(TestServer.BASE_URI);

        query = new Query();
        query.setCrawlingInterval(180);
        query.setQueryContent("Looking for a meaning of life");
        query.setAccuracyCap(0.3);
        query.setOwnerId("Drunk philosopher");
        query.setStatus("ready");

        Query query2 = new Query();
        query2.setCrawlingInterval(180);
        query2.setQueryContent("Looking for a 56th digit of pi");
        query2.setAccuracyCap(0.53);
        query2.setOwnerId("Math student on LSD");
        query2.setStatus("ready");

        SERVER.QUERY_DAO.save(query);
        SERVER.QUERY_DAO.save(query2);

        ArrayList<Query> queries = new ArrayList<>();
        queries.add(query);
        queries.add(query2);
        validResponse = SERVER.WRITER.writeValueAsString(queries);
    }

    @After
    public void tearDown() {
        SERVER.CONNECTOR.getDatabase().dropDatabase();
        server.shutdown();
    }

    @Test
    public void testGetQueries() {
        Response invoke1 = target.path(queryPath).request(MediaType.APPLICATION_JSON_TYPE).buildGet().invoke();
        assertEquals(invoke1.getMediaType(), MediaType.APPLICATION_JSON_TYPE);
        assertEquals(invoke1.getStatus(), 200);
        String invoke = target.path(queryPath).request().get(String.class);
        assertEquals(invoke, validResponse);
    }

    @Test
    public void testGetQueryById() {
        String path = queryPath + "/query_id=" + query.getQueryId();
        Response invoke1 = target.path(path).request(MediaType.APPLICATION_JSON_TYPE).buildGet().invoke();
        assertEquals(invoke1.getMediaType(), MediaType.APPLICATION_JSON_TYPE);
        assertEquals(invoke1.getStatus(), 200);
        String invoke = target.path(path).request().get(String.class);
        try {
            assertEquals(invoke, SERVER.WRITER.writeValueAsString(query));
        } catch (JsonProcessingException e) {
            fail();
        }
    }

    @Test
    public void testPostQuery() {
        Query query3 = new Query();
        query3.setCrawlingInterval(180);
        query3.setQueryContent("I could use some wine. Fetch me some wine, Siri");
        query3.setAccuracyCap(0.53);
        query3.setOwnerId("Sleepy student");
        query3.setStatus("ready");

        try {
            String jsonString = SERVER.WRITER.writeValueAsString(query3);
            Entity<String> json = Entity.json(jsonString);
            target.path(queryPath).request().buildPost(json).invoke();
            assertEquals(SERVER.QUERY_DAO.getCollection().count(), 3);
        } catch (JsonProcessingException e) {
            fail();
        }
    }

}
