package player.implementation;

import network.framework.Network;
import round.framework.Round;
import ai.framework.AI;
import ai.framework.AIFactory;

public class AutomatedPlayer extends PlayerTemplate {
    private AI ai;
    
    public AutomatedPlayer(Network network){
        this.ai = AIFactory.getAI("Simple", network);
    }
    
    public void decideDiscarding(){
        
    }
    
    public int getOpeningBet(){
        
        return 0;
    }
    
    public void decideStrategy(Round round){
        this.ai.decideStrategy(super.hand, round);
    }
}
