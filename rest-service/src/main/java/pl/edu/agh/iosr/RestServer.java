package pl.edu.agh.iosr;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import pl.edu.agh.iosr.model.MongoConnector;
import pl.edu.agh.iosr.model.Query;
import pl.edu.agh.iosr.model.QueryDAO;

import java.io.IOException;
import java.net.URI;

/**
 * RestServer class.
 */
public class RestServer {
    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://localhost:8055/myapp/";

    public static MongoConnector CONNECTOR;

    public static ObjectMapper MAPPER;

    public static ObjectWriter WRITER;

    public static QueryDAO QUERY_DAO;

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     *
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        CONNECTOR = new MongoConnector("queries", "localhost", 27017, Query.class);
        MAPPER = new ObjectMapper();
        WRITER = MAPPER.writerWithDefaultPrettyPrinter();
        QUERY_DAO = new QueryDAO(CONNECTOR.getClient(), CONNECTOR.getMorphia(), CONNECTOR.getDbName());
        // create a resource config that scans for JAX-RS resources and providers
        // in pl.edu.agh.iosr package
        final ResourceConfig rc = new ResourceConfig().packages("pl.edu.agh.iosr.routes");

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    /**
     * RestServer method.
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
        System.in.read();
        server.stop();
    }
}

