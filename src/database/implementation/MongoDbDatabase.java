package database.implementation;

import database.framework.Database;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

public class MongoDbDatabase implements Database {
	private static MongoCollection<Document> handCollection;
	private static MongoCollection<Document> leaderCollection;
	private MongoDatabase database;
	private String hand = "Hand Frequencies";
	private String leader = "Leaderboard";

	/**
	 * Constructor method.
	 * Opens connection to MongoDB, pulls the database and then calls the setup method
	 */
	public MongoDbDatabase() {
		MongoClientURI uri = new MongoClientURI(
				"mongodb://Pokerlingus:poketweeter$17@pokerlingus-shard-00-00-aqumh.mongodb.net:27017,pokerlingus-shard-00-01-aqumh.mongodb.net:27017,pokerlingus-shard-00-02-aqumh.mongodb.net:27017/Pokerlingus?ssl=true&replicaSet=Pokerlingus-shard-0&authSource=admin");
		
		MongoClient mongoClient = new MongoClient(uri);		
		MongoDatabase db = mongoClient.getDatabase("Pokerlingus");
		
		this.database=db;
		
        MongoCollection<Document> dbColl = database.getCollection(hand);
        MongoCollection<Document> dbColl1 = database.getCollection(leader);
        
        MongoDbDatabase.handCollection=dbColl;
        MongoDbDatabase.leaderCollection=dbColl1;
        
        setup(getPlayerList(), getHandList());
	}
	
	/**
	 * Adds the players and hand types to their respective collections
	 * @param players
	 * @param hands
	 */
	public void setup(Vector<String> players, Vector<String> hands) {
		dropDatabase();
		
		Document doc = new Document("Title", "Hand Frequencies");
	    for (int i=0; i<hands.size(); i++){
	    	doc.append(hands.get(i), 0);
	    }
	    insertIntoDB(doc, handCollection);
	        
	    Document doc1 = new Document("Title", "Leaderboard");
	    for (int i=0; i<players.size(); i++){
	    	doc1.append(players.get(i), 0);
	    }
	    insertIntoDB(doc1, leaderCollection);
	}
	
	/**
	 * Prints the specific collection to the console
	 * @param collection
	 */
	public void displayContents(MongoCollection<Document> collection) {
		MongoCursor<Document> dbCursor = collection.find().iterator();
        try {
	        while (dbCursor.hasNext()) {
	        	System.out.println(dbCursor.next());
	        }
        } finally {
            dbCursor.close();
        }
	}
	
	/**
	 * Deletes the collections in the database if necessary
	 */
	public void dropDatabase() {
        handCollection.drop();
        leaderCollection.drop();
	}
	
	/**
	 * Inserts a document to a specific collection in the database
	 * @param doc
	 * @param collection
	 */
	public void insertIntoDB(Document doc, MongoCollection<Document> collection) {
		collection.insertOne(doc);
	}
	
	/**
	 * Increments a value in a collection. e.g., when a player wins, his win count increments by one.
	 * @param key The hand type or player win count to be incremented
	 * @param collection The collection in which the incrementation will occur
	 */
	public void incrementValue(String key, MongoCollection<Document> collection) {
		Document newDocument =
				new Document().append("$inc",
				new Document().append(key, 1));

		collection.updateOne(new Document(), newDocument);
	}
	
	/**
	 * Adds the players to an Vector
	 * @return Vector of the players
	 */
	public Vector<String> getPlayerList() {		// needs the actual player list to be called into here
		Vector<String> players = new Vector<String>();
		players.add("Player 1");
		players.add("AI Player 1");
		players.add("AI Player 2");
		players.add("AI Player 3");

		return players;
	}
	
	/**
	 * Adds the hand types to a Vector
	 * @return Vector of the hand types
	 */
	public Vector<String> getHandList() {
		Vector<String> hands = new Vector<String>();
		hands.add("Highhand");
		hands.add("One Pair");
		hands.add("Two Pair");
		hands.add("Three of a Kind");
		hands.add("Flush");
		hands.add("Straight");
		hands.add("Full House");
		hands.add("Four of a Kind");
		hands.add("Straight Flush");
		hands.add("Royal Flush");

		return hands;
	}
	
	/**
	 * Retrieves the statistics for the leaderboard or hand frequencies
	 * @param collection
	 * @return sorted statistics as a hash map in descending order of values
	 */
	public Map<String, Integer> getStatistics(MongoCollection<Document> collection) {
		Vector<String> vector = new Vector<String>();
		Map<String, Integer> stats = new HashMap<String, Integer>();
		String name=null;
		
		if (collection.equals(handCollection)){
			vector=getHandList();
			name = "Hand Frequencies";
		} else if (collection.equals(leaderCollection)) {
			vector=getPlayerList();
			name = "Leaderboard";
		} 

		for (int i=0; i<vector.size(); i++) {
			Document document = collection
					.find(new Document("Title", name))
					.projection(Projections.fields(Projections.include(vector.get(i)), Projections.excludeId())).first();
			int number = document.getInteger(vector.get(i));
			stats.put(vector.get(i), number);
		}
		stats=sortByValue(stats);
		return stats;
	}
	
	/**
	 * Sorts the Map created by getStatistics() into descending order
	 * 
	 * @param map
	 * @return sorted map
	 */
	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
	    List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
	    Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
	        public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
	            return (o2.getValue()).compareTo(o1.getValue() );
	        }
	    } );
	
	    Map<K, V> result = new LinkedHashMap<K, V>();
    	
	    for (Map.Entry<K, V> entry : list){
    		result.put(entry.getKey(), entry.getValue());
    	}
	    return result;
	}
	

	public static void main(String[] args) throws UnknownHostException{			        
		MongoDbDatabase database = new MongoDbDatabase();
		
		database.incrementValue("Player 1", leaderCollection);
		database.incrementValue("Player 1", leaderCollection);
		database.incrementValue("Player 1", leaderCollection);
		database.incrementValue("AI Player 1", leaderCollection);
		database.incrementValue("AI Player 2", leaderCollection);
		
		database.incrementValue("Highhand", handCollection);
		database.incrementValue("Two Pair", handCollection);
		database.incrementValue("Two Pair", handCollection);
		database.incrementValue("Three of a King", handCollection);
		database.incrementValue("Straight", handCollection);
		database.incrementValue("Straight", handCollection);
		database.incrementValue("Straight", handCollection);
		database.incrementValue("Flush", handCollection);

		database.displayContents(leaderCollection);
		database.displayContents(handCollection);

		System.out.println("Hand Statistics = " + database.getStatistics(handCollection));
	    System.out.println("Leaderboard = " + database.getStatistics(leaderCollection));

		    
		}
	}
