package ai.implementation;

import game.framework.Game;

/**
 * Complex AI
 * Expresses Personality while formulating strategy
 * @author Rory Buckley
 *
 */
public class AIComplex extends AITemplate {
    AIComplex(Game game, Personality personality){
        super(game, personality);
    }
    
    /**
     * Complex AI expresses confidence and behaviour based on personality and the current strategy
     * @param strategy
     */
    protected void expressPersonality(Strategy strategy){
        
    }
}
