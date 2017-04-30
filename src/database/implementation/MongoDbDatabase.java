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
	 * Retrieves the number of wins each player has and sorted them in descending order
	 *  
	 * @return sorted leaderboard as a hash map in descending order  
	 */
	public Map<String, Integer> getLeaderboard() {
		Vector<String> players = new Vector<String>();
		Map<String, Integer> leaderboard = new HashMap<String, Integer>();
		players = getPlayerList();

		for (int i=0; i<players.size(); i++) {
			Document document = leaderCollection
					.find(new Document("Title", "Leaderboard"))
					.projection(Projections.fields(Projections.include(players.get(i)), Projections.excludeId())).first();
			int number = document.getInteger(players.get(i));
			leaderboard.put(players.get(i), number);
		}
		leaderboard=sortByValue(leaderboard);
		return leaderboard;
	}
	
	/**
	 * Sorts the Map created by getLeaderBoard() into descending order, as a leaderboard should.
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
		MongoDbDatabase insert = new MongoDbDatabase();
		
	    insert.incrementValue("Player 1", leaderCollection);
	    insert.incrementValue("Player 1", leaderCollection);
	    insert.incrementValue("Player 1", leaderCollection);
	    insert.incrementValue("AI Player 1", leaderCollection);
	    insert.incrementValue("AI Player 2", leaderCollection);

	    insert.displayContents(leaderCollection);
	    insert.displayContents(handCollection);
	    System.out.println("Leaderboard = " + insert.getLeaderboard());
		    
		}
	}
