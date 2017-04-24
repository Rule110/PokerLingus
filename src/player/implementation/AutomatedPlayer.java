package player.implementation;

import game.framework.Game;
import round.framework.Round;
import ai.framework.AI;
import ai.framework.AIFactory;
import ai.implementation.DiscardStrategy;
import ai.implementation.Strategy;

public class AutomatedPlayer extends PlayerTemplate {
    private AI ai;
    private Strategy strategy;
    
    public AutomatedPlayer(Game game){
        this.ai = AIFactory.getAI("Simple", game);
    }
    
    public void decideDiscarding(){
        DiscardStrategy discardStrategy = this.ai.decideDiscarding(hand);
        super.discardindices = discardStrategy.getDiscardableCards();
    }
    
    public int getOpeningBet(){
        
        return 0;
    }
    
    public void decideStrategy(Round round){
        RoundState roundState = new RoundState(round, super.ID);
        this.strategy = this.ai.decideStrategy(super.hand, roundState);
    }
    
    public boolean isFolding(){
        boolean isFolding = this.strategy.isFolding();
        if (isFolding){
            this.strategy = null;
        }
        return isFolding;
    }
    
    public boolean isCalling(){
        boolean isCalling = this.strategy.isCalling();
        if (isCalling){
            this.strategy = null;
        }
        return isCalling;
    }
    
    public boolean isRaising(){
        return this.strategy.isRaising();
    }
    
    public int getRaise(){
        int raiseAmount = this.strategy.getRaiseAmount();
        this.strategy = null;
        return raiseAmount;
    }
}
