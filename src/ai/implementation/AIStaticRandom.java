package ai.implementation;

import game.framework.Game;

/**
 * Static Random AI
 * AI that is unintelligent and is randomly generated each time
 * @author Rory Buckley
 *
 */
public class AIStaticRandom extends AIComplex {
    public AIStaticRandom(Game game, String playerID){
        super(game, playerID, new Personality());
    }
}
