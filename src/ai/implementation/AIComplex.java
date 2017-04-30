package ai.implementation;

import java.util.concurrent.ThreadLocalRandom;

import textupdate.framework.TextUpdate;
import textupdate.framework.TextUpdateFactory;
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
        
        TextUpdate aiConfidenceUpdate = 
                TextUpdateFactory.getTextUpdate("AIConfidence", super.name, confidenceUpdateCode);
        super.game.pushTextUpdate(aiConfidenceUpdate);
        
        TextUpdate aiTellsUpdate = 
                TextUpdateFactory.getTextUpdate("AITells", super.name, tellsUpdateCode);
        super.game.pushTextUpdate(aiTellsUpdate);
        
        TextUpdate aiBragsUpdate = 
                TextUpdateFactory.getTextUpdate("AIBrags", super.name, bragsUpdateCode);
        super.game.pushTextUpdate(aiBragsUpdate);
    }
}
