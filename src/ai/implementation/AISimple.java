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
    public AISimple(Game game){
        super(game, new Personality());
    }
    
    /**
     * Simple AI doesn't express based on its personality and the strategy
     * @param strategy
     */
    protected void expressPersonality(Strategy strategy){
        // Simple AI doesn't express Personality
    }
}
