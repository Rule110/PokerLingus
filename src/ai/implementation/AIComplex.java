package ai.implementation;

import java.util.concurrent.ThreadLocalRandom;

import pokerfaice.Parser;
import game.framework.Game;

/**
 * Complex AI
 * Expresses Personality while formulating strategy
 * @author Rory Buckley
 *
 */
public class AIComplex extends AITemplate {
    AIComplex(Game game, String playerID, Personality personality){
        super(game, playerID, personality);
    }
    
    /**
     * Complex AI expresses confidence and behaviour based on personality and the current strategy
     * @param strategy
     */
    protected void expressPersonality(Strategy strategy){
        int confidenceUpdateCode = strategy.getBluffedConfidence().getIntegerRepresentation() - 1;
        int tellsUpdateCode = strategy.getBehaviour().getBehaviourNumber();
        int bragsUpdateCode = ThreadLocalRandom.current().nextInt(0, 10);
        
        Parser parser = super.game.getParser();
        parser.pushUpdateToGame(super.game, "AIConfidence", super.name, confidenceUpdateCode);
        parser.pushUpdateToGame(super.game, "AITells", super.name, tellsUpdateCode);
        parser.pushUpdateToGame(super.game, "AIBrags", super.name, bragsUpdateCode);
    }
}
