package pl.edu.agh.iosr.nlp.keywords;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.Cursor;
import com.mongodb.DBObject;

public class KeywordDocController {
	private final MongoProxy proxy;
	
	public KeywordDocController() {
		proxy = new MongoProxy();
	}
	
	public void addNewKeywords(List<String> keywords, String category){
		DBObject object = BasicDBObjectBuilder.start().append("category", category).append("keywords", keywords).get();
		proxy.addDocument(object);
	}
	
	public Iterator<List<String>> getCategoryKeywordsIterator(String category){
		DBObject query = BasicDBObjectBuilder.start().append("category", category).get();
		Cursor cursor = proxy.getDocuments(query);
		return new Iterator<List<String>>() {

			@Override
			public boolean hasNext() {
				return cursor.hasNext();
			}

			@Override
			public List<String> next() {
				DBObject object = cursor.next();
				List<String> keywords = (List<String>)object.get("keywords");
				return keywords;
			}
		};
	}	
}
