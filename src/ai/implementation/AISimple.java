package ai.implementation;

import game.framework.Game;

/**
 * Simple AI
 * Formulates Strategy numerically
 * Lacks expressions of Behaviour or Confidence
 * @author Rory Buckley
 *
 */
public class AISimple extends AITemplate {
    /**
     * Constructor with randomly created Personality
     * @param game
     * @param playerID
     */
    public AISimple(Game game, String playerID){
        super(game, playerID, new Personality());
    }
    
    /**
     * Constructor where personality can be input
     * @param game
     * @param playerID
     * @param personality
     */
    public AISimple(Game game, String playerID, Personality personality){
        super(game, playerID, personality);
    }
    
    /**
     * Simple AI doesn't express based on its personality and the strategy
     * @param strategy
     */
    protected void expressPersonality(Strategy strategy){
        // Simple AI doesn't express Personality
    }
}
