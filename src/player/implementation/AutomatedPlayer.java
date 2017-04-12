package player.implementation;

import round.framework.Round;
import ai.framework.AI;
import ai.framework.AIFactory;

public class AutomatedPlayer extends PlayerTemplate {
    private AI ai;
    
    public AutomatedPlayer(){
        this.ai = AIFactory.getAI("Simple");
    }
    
    @Override
    public void decideStrategy(Round round){
        
    }
}
