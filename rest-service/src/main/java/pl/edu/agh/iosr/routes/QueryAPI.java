package pl.edu.agh.iosr.routes;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("queries")
public class QueryAPI {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String get() {
        return "Got it!";
    }

    @POST
    @Consumes("application/json")
    public void post() {

    }
}
