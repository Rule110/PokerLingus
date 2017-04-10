package player.implementation;

import round.framework.RoundState;
import ai.framework.AI;
import ai.framework.AIFactory;

public class AutomatedPlayer extends PlayerTemplate {
    private AI ai;
    
    public AutomatedPlayer(){
        this.ai = AIFactory.getAI("Simple");
    }
    
    public void decideStrategy(RoundState info){
        
    }
}
