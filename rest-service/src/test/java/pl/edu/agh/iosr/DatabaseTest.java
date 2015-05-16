package pl.edu.agh.iosr;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBObject;
import org.junit.Before;
import org.junit.Test;
import pl.edu.agh.iosr.model.MongoConnector;

import static org.junit.Assert.*;

public class DatabaseTest {

    private String testCollectionName = "testCollection";

    @Test
    public void ifDatabaseExists(){
        MongoConnector testDb = new TestDatabaseConnector();
        DB database = testDb.getDatabase();
        assertNotNull(database);
        database.dropDatabase();
        DBObject options = BasicDBObjectBuilder.start().add("capped", true).add("size", 1024*1024).get();
        database.createCollection(testCollectionName, options);
        assertTrue(database.collectionExists(testCollectionName));
        database.dropDatabase();
        assertFalse(database.collectionExists(testCollectionName));
        assertEquals(database.getCollectionNames().size(), 0);
    }
}
