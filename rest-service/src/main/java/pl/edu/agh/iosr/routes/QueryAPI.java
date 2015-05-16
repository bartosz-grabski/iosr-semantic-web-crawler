package pl.edu.agh.iosr.routes;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Key;
import pl.edu.agh.iosr.RestServer;
import pl.edu.agh.iosr.model.Query;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Path("/queries")
public class QueryAPI {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String get() {
        try {
            return RestServer.WRITER.writeValueAsString(RestServer.QUERY_DAO.find().asList());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "error2";
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String post(String json) {
        try {
            Query query = RestServer.MAPPER.readValue(json, Query.class);
            Key<Query> save = RestServer.QUERY_DAO.save(query);
            return save.getId().toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }

    @GET
    @Path("/query_id={query_id}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public String getQueryById(@PathParam("query_id") String queryId){
        Query query = RestServer.QUERY_DAO.get(new ObjectId(queryId));
        try {
            String result = RestServer.WRITER.writeValueAsString(query);
            System.out.println(result);
            return result;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "mucherror";
        }
    }

    @GET
    @Path("/owner_id={owner_id}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public String getQueryByOwner(@PathParam("owner_id") String ownerId){
        List<ObjectId> owner_id = RestServer.QUERY_DAO.findIds("ownerId", ownerId);
        List<Query> results = new ArrayList<>();
        for(ObjectId id: owner_id){
            results.add(RestServer.QUERY_DAO.get(id));
        }
        try {
            return RestServer.WRITER.writeValueAsString(results);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "bladblad";
        }
    }
    @GET
    @Path("/owner_id={owner_id}/status={status}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public String getQueryByOwnerAndStatus(@PathParam("owner_id") String ownerId,
                                           @PathParam("status") String status){
        List<ObjectId> owner_id = RestServer.QUERY_DAO.findIds("ownerId", ownerId);
        List<Query> results = new ArrayList<>();
        for(ObjectId id: owner_id) {
            Query query = RestServer.QUERY_DAO.get(id);
            if(query.getStatus().equals(status)){
                results.add(query);
            }
        }
        try {
            return RestServer.WRITER.writeValueAsString(results);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "bladblad";
        }
    }
}
