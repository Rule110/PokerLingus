package ai.implementation;

import java.util.List;

import game.framework.Game;

public class PersistentPersonality {
    public static Personality retrieve(Game game, String type){
        List<String> personalityIDs = game.getDatabase().getAIPersonalityIDList(type);
        
        String personalityID = personalityIDs.get(0);
        
        return (Personality)game.getDatabase().retrieve(personalityID);
    }
}
