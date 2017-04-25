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
    
    /**
     * Discarded Cards are decided in Automated Player by asking for
     *  a Discard Strategy from the AI.
     */
    public void decideDiscarding(){
        DiscardStrategy discardStrategy = this.ai.decideDiscarding(hand);
        super.discardindices = discardStrategy.getDiscardableCards();
    }
    
    /**
     * The Opening Bet is decided in Automated Player by
     *  asking the AI to decide.
     */
    public int getOpeningBet(Round round){
        RoundState roundState = new RoundState(round, super.ID);
        return this.ai.decideOpening(hand, roundState);
    }
    
    /**
     * The Strategy for the current state of the Round is
     *  decided by asking the AI to decide a Strategy based on
     *   the current Round State.
     */
    public void decideStrategy(Round round){
        RoundState roundState = new RoundState(round, super.ID);
        this.strategy = this.ai.decideStrategy(super.hand, roundState);
    }
    
    /**
     * The AI's last decided Strategy is queried to assess is it Folding
     *  The Strategy is then deleted if it is
     */
    public boolean isFolding(){
        boolean isFolding = this.strategy.isFolding();
        if (isFolding){
            this.strategy = null;
        }
        return isFolding;
    }
    
    /**
     * The AI's last decided Strategy is queried to assess is it Calling
     *  The Strategy is then deleted if it is
     */
    public boolean isCalling(){
        boolean isCalling = this.strategy.isCalling();
        if (isCalling){
            this.strategy = null;
        }
        return isCalling;
    }
    
    /**
     * The AI's last decided Strategy is queried to assess is it Raising
     *  The Strategy is kept until the Raise Amount is queried
     */
    public boolean isRaising(){
        return this.strategy.isRaising();
    }
    
    /**
     * The AI's last decided Strategy is queried to get the Amount it will Raise by
     *  The Strategy is then deleted if it is
     */
    public int getRaise(){
        int raiseAmount = this.strategy.getRaiseAmount();
        this.strategy = null;
        return raiseAmount;
    }
}
