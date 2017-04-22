package ai.framework;

import game.framework.Game;
import ai.implementation.ComplexDynamicPersistentAI;
import ai.implementation.ComplexStaticPersistentAI;
import ai.implementation.ComplexStaticRandomAI;
import ai.implementation.SimpleAI;

public class AIFactory {
    public static AI getAI(String type, Game game){
        AI ai;
        
        switch (type){
        case "Simple":
            ai = new SimpleAI(game);
            break;
        case "ComplexStaticRandom":
            ai = new ComplexStaticRandomAI(game);
            break;
        case "ComplexStaticPersistent":
            ai = new ComplexStaticPersistentAI(game);
            break;
        case "ComplexDynamicPersistent":
            ai = new ComplexDynamicPersistentAI(game);
            break;
        default:
            throw new RuntimeException();
        }
        
        return ai;
    }
}
