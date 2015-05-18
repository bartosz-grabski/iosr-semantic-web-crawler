package pl.edu.agh.iosr.routes;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Key;
import pl.edu.agh.iosr.RestApp;
import pl.edu.agh.iosr.model.Query;

import javax.ws.rs.*;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Path("/queries")
public class QueryAPI {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get() {
        try {
            String json = RestApp.SERVER.WRITER.writeValueAsString(RestApp.SERVER.QUERY_DAO.find().asList());
            Response response = Response.ok(json, MediaType.APPLICATION_JSON_TYPE).header("Access-Control-Allow-Origin", "*").build();
            return response;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return Response.status(500).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String post(String json) {
        try {
            Query query = RestApp.SERVER.MAPPER.readValue(json, Query.class);
            Key<Query> save = RestApp.SERVER.QUERY_DAO.save(query);
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
    public String getQueryById(@PathParam("query_id") String queryId) {
        Query query = RestApp.SERVER.QUERY_DAO.get(new ObjectId(queryId));
        try {
            String result = RestApp.SERVER.WRITER.writeValueAsString(query);
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
    public String getQueryByOwner(@PathParam("owner_id") String ownerId) {
        List<ObjectId> owner_id = RestApp.SERVER.QUERY_DAO.findIds("ownerId", ownerId);
        List<Query> results = new ArrayList<>();
        for (ObjectId id : owner_id) {
            results.add(RestApp.SERVER.QUERY_DAO.get(id));
        }
        try {
            return RestApp.SERVER.WRITER.writeValueAsString(results);
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
                                           @PathParam("status") String status) {
        List<ObjectId> owner_id = RestApp.SERVER.QUERY_DAO.findIds("ownerId", ownerId);
        List<Query> results = new ArrayList<>();
        for (ObjectId id : owner_id) {
            Query query = RestApp.SERVER.QUERY_DAO.get(id);
            if (query.getStatus().equals(status)) {
                results.add(query);
            }
        }
        try {
            return RestApp.SERVER.WRITER.writeValueAsString(results);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "bladblad";
        }
    }
}
