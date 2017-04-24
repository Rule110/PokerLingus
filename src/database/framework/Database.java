package database.framework;

import java.util.ArrayList;

public interface Database {
    public Object retrieve(String id);
    
    public ArrayList<String> getAIPersonalityIDList(String type);
}
