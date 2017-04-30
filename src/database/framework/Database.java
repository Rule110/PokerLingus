package database.framework;

import java.util.Map;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

public interface Database {
	public void displayContents(MongoCollection<Document> collection);
	
	public void dropDatabase();
	
	public void insertIntoDB(Document doc, MongoCollection<Document> collection);
	
	public void incrementValue(String key, MongoCollection<Document> collection);
		
	public Map<String, Integer> getStatistics(MongoCollection<Document> collection);


}
