package pl.edu.agh.iosr;

import pl.edu.agh.iosr.model.MongoConnector;
import pl.edu.agh.iosr.model.Query;

public class TestDatabaseConnector extends MongoConnector {

    public TestDatabaseConnector(){
        super("testDatabaseDb", "localhost", 27017, Query.class);
        getDatabase().dropDatabase();
    }
}
