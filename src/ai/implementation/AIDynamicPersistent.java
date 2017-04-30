package ai.implementation;

import game.framework.Game;

/**
 * Dynamic Persistent AI
 * Complex AI with intelligence and persistence
 * @author Rory Buckley
 *
 */
public class AIDynamicPersistent extends AIComplex {
    
    public AIDynamicPersistent(Game game, String playerID){
        
        super(game, playerID, PersistentPersonality.retrieve(game, playerID, "Dynamic"));
    }
}
