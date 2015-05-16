package pl.edu.agh.iosr.nlp.keywords;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class MongoProxy {
	private MongoClient client;
	private DB database;
	
	public MongoProxy() {
		try{
			client = new MongoClient();			
			database = client.getDB("nlp");
		}catch(UnknownHostException uhe){
			System.out.println("connection failed");
		}
	}
	
	public void addDocument(DBObject doc){
		DBCollection coll = database.getCollection("keywords");
		coll.insert(doc);
	}
	
	public DBCursor getDocuments(DBObject query){
		DBCollection coll = database.getCollection("keywords");
		DBCursor cursor = coll.find(query);
		return cursor;
	}
}
