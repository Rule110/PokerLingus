package player.implementation;

import game.framework.Game;
import round.framework.Round;
import ai.framework.AI;
import ai.framework.AIFactory;

public class AutomatedPlayer extends PlayerTemplate {
    private AI ai;
    
    public AutomatedPlayer(Game game){
        this.ai = AIFactory.getAI("Simple", game);
    }
    
    public void decideDiscarding(){
        
    }
    
    public int getOpeningBet(){
        
        return 0;
    }
    
    public void decideStrategy(Round round){
        RoundState roundState = new RoundState(round, super.ID);
        this.ai.decideStrategy(super.hand, roundState);
    }
}
