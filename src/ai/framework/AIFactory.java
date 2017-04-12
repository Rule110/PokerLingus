package ai.framework;

import network.framework.Network;
import ai.implementation.ComplexDynamicPersistentAI;
import ai.implementation.ComplexStaticPersistentAI;
import ai.implementation.ComplexStaticRandomAI;
import ai.implementation.SimpleAI;

public class AIFactory {
    public static AI getAI(String type, Network network){
        AI ai;
        
        switch (type){
        case "Simple":
            ai = new SimpleAI(network);
            break;
        case "ComplexStaticRandom":
            ai = new ComplexStaticRandomAI(network);
            break;
        case "ComplexStaticPersistent":
            ai = new ComplexStaticPersistentAI(network);
            break;
        case "ComplexDynamicPersistent":
            ai = new ComplexDynamicPersistentAI(network);
            break;
        default:
            throw new RuntimeException();
        }
        
        return ai;
    }
}
