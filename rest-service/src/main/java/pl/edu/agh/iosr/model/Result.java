package pl.edu.agh.iosr.model;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.ArrayList;

@Entity
public class Result {

    @Id
    private ObjectId resultId;
    private ObjectId queryId;
    private ArrayList<Object> url;

}
