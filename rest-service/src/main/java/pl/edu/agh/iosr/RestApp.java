package pl.edu.agh.iosr;

import org.glassfish.grizzly.http.server.HttpServer;

import java.io.IOException;


public class RestApp {
    public static RestServer SERVER;

    public RestApp(RestServer server){
        SERVER = server;
    }

    public RestApp(){
        SERVER = new RestServer();
    }

    public static void main(String[] args) throws IOException {
        final HttpServer server = SERVER.startServer();
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", SERVER.BASE_URI));
        System.in.read();
        server.shutdown();
    }
}
