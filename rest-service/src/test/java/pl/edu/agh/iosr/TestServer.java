package pl.edu.agh.iosr;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import pl.edu.agh.iosr.model.QueryDAO;

import java.net.URI;

public class TestServer extends RestServer{

    @Override
    public HttpServer startServer() {
        CONNECTOR = new TestDatabaseConnector();
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
}
