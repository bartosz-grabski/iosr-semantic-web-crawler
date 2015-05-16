package pl.edu.agh.iosr.model;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import org.mongodb.morphia.Morphia;

import java.net.UnknownHostException;

public class MongoConnector {

    private MongoClient client;
    private DB db;
    private Morphia morphia;


    public MongoConnector(String dbName, String dbAddress, int dbPort, Class... morphiaClasses) {
        try {
            client = new MongoClient(dbAddress, dbPort);
            db = client.getDB(dbName);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        morphia = new Morphia();
        for(Class c: morphiaClasses){
            morphia.map(c);
        }
    }

    public DB getDatabase() {
        return db;
    }

    public MongoClient getClient(){
        return client;
    }

    public String getDbName(){
        return db.getName();
    }

    public Morphia getMorphia(){
        return morphia;
    }


}
