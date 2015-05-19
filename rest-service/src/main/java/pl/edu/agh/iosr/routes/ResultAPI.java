package pl.edu.agh.iosr.routes;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.QueryResults;
import pl.edu.agh.iosr.RestApp;
import pl.edu.agh.iosr.model.Result;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;

@Path("/results")
public class ResultAPI extends AbstractAPI{

    @GET
    @Path("/query_id={query_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getQueryResults(@PathParam("query_id") String queryId){
        System.out.println(queryId);
        QueryResults<Result> results = RestApp.SERVER.RESULT_DAO.find();
//        Query<Result> resultsQuery = RestApp.SERVER.RESULT_DAO.getDatastore().createQuery(Result.class).field("query_id").equal(queryId);
//        QueryResults<Result> results = RestApp.SERVER.RESULT_DAO.find(resultsQuery);
        try {
            String json = RestApp.SERVER.WRITER.writeValueAsString(results.asList());
            System.out.println(json);
            return makeCORS(Response.ok(json, MediaType.APPLICATION_JSON_TYPE), "");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return Response.status(500).build();
        }
    }
}
