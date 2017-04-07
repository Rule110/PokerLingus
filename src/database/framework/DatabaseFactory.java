package database.framework;

import database.implementation.MongoDbDatabase;

public class DatabaseFactory {
    public static Database getDatabase(String type){
        Database db;
        
        switch (type){
        case "MongoDb":
            db = new MongoDbDatabase();
            break;
        default:
            throw new RuntimeException();
        }
        
        return db;
    }
}
