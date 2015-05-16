package pl.edu.agh.iosr;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import java.util.concurrent.Executors;

import static org.junit.Assert.assertEquals;

/**
 * Created by bambo on 5/16/15.
 */
public class QueryAPITest {

    private HttpServer server;
    private WebTarget target;

    private String queryPath ="queries";

    @Before
    public void setUp() throws Exception {
        // start the server
        server = Main.startServer();
        // create the client
        Client c = ClientBuilder.newClient();
        target = c.target(Main.BASE_URI);
    }

    @Test
    public void testGetIt() {
        String responseMsg = target.path(queryPath).request().get(String.class);
        System.out.println(responseMsg);
        assertEquals("Got it!", responseMsg);
    }

    @Test
    public void testPostQuery(){

    }

}
