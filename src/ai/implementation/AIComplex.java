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
    AIComplex(Game game, Personality personality){
        super(game, personality);
    }
    
    /**
     * Complex AI expresses confidence and behaviour based on personality and the current strategy
     * @param strategy
     */
    protected void expressPersonality(Strategy strategy){
        int confidenceUpdateCode = strategy.getBluffedConfidence().getIntegerRepresentation() - 1;
        int tellsUpdateCode = strategy.getBehaviour().getBehaviourNumber();
        int bragsUpdateCode = ThreadLocalRandom.current().nextInt(0, 10);
        
        TextUpdate aiConfidenceUpdate = TextUpdateFactory.getTextUpdate("AIConfidence", confidenceUpdateCode);
        super.game.pushTextUpdate(aiConfidenceUpdate);
        
        TextUpdate aiTellsUpdate = TextUpdateFactory.getTextUpdate("AITells", tellsUpdateCode);
        super.game.pushTextUpdate(aiTellsUpdate);
        
        TextUpdate aiBragsUpdate = TextUpdateFactory.getTextUpdate("AIBrags", bragsUpdateCode);
        super.game.pushTextUpdate(aiBragsUpdate);
    }
}
