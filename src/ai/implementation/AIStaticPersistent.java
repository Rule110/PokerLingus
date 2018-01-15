package ai.implementation;

import game.framework.Game;

/**
 * Static Persistent AI
 * Unintelligent AI that is persistently stored
 * @author Rory Buckley
 *
 */
public class AIStaticPersistent extends AIComplex {
    
    public AIStaticPersistent(Game game, String playerID){
        
        super(game, playerID, PersistentPersonality.retrieve(game, playerID, "Static"));
    }
}
