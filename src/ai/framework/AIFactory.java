package ai.framework;

import ai.implementation.ComplexDynamicPersistentAI;
import ai.implementation.ComplexStaticPersistentAI;
import ai.implementation.ComplexStaticRandomAI;
import ai.implementation.SimpleAI;

public class AIFactory {
    public static AI getAI(String type){
        AI ai;
        
        switch (type){
        case "Simple":
            ai = new SimpleAI();
            break;
        case "ComplexStaticRandom":
            ai = new ComplexStaticRandomAI();
            break;
        case "ComplexStaticPersistent":
            ai = new ComplexStaticPersistentAI();
            break;
        case "ComplexDynamicPersistent":
            ai = new ComplexDynamicPersistentAI();
            break;
        default:
            throw new RuntimeException();
        }
        
        return ai;
    }
}
