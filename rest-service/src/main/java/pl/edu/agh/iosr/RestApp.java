package pl.edu.agh.iosr;

import org.glassfish.grizzly.http.server.HttpServer;

import java.io.IOException;


public class RestApp {
    public static String HOST_ADDRESS= "http://localhost:8055";

    public static String DB_ADDRESS = "localhost";

    public static String SCRAPYD_ADDRESS = "localhost:6800";

    public static RestServer SERVER;

    public RestApp(RestServer server) {
        SERVER = server;
    }

    public RestApp() {
        SERVER = new RestServer();
    }

    public static void main(String[] args) throws IOException {
        RestApp app = new RestApp();
        HOST_ADDRESS = args[0];
        DB_ADDRESS = args[1];
        SCRAPYD_ADDRESS = args[2];
        final HttpServer server = app.SERVER.startServer(args[0], args[1]);
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", args[0]));
        System.in.read();
        server.shutdown();
    }
}
