package ai.framework;

import game.framework.Game;
import ai.implementation.AIDynamicPersistent;
import ai.implementation.AIStaticPersistent;
import ai.implementation.AIStaticRandom;
import ai.implementation.AISimple;

/**
 * Factory for AI module implementation
 * @author Rory Buckley
 *
 */
public class AIFactory {
    public static AI getAI(String type, Game game){
        AI ai;
        
        switch (type){
        case "Simple":
            ai = new AISimple(game);
            break;
        case "ComplexStaticRandom":
            ai = new AIStaticRandom(game);
            break;
        case "ComplexStaticPersistent":
            ai = new AIStaticPersistent(game);
            break;
        case "ComplexDynamicPersistent":
            ai = new AIDynamicPersistent(game);
            break;
        default:
            throw new RuntimeException();
        }
        
        return ai;
    }
}
