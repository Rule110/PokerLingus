package ai.implementation;

import game.framework.Game;

/**
 * Static Persistent AI
 * Unintelligent AI that is persistently stored
 * @author Rory Buckley
 *
 */
public class AIStaticPersistent extends AIComplex {
    
    public AIStaticPersistent(Game game){
        
        super(game, PersistentPersonality.retrieve(game, "Static"));
    }
}
