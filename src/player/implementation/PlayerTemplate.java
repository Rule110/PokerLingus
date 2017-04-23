package player.implementation;

import java.util.Vector;

import hand.framework.Hand;
import hand.implementation.OnePair;
import hand.implementation.PlayingCard;
import player.framework.Player;
import round.framework.Round;

abstract public class PlayerTemplate implements Player {
	protected String ID;
    protected Hand hand;
    protected Vector<Integer> discardindices;
    protected boolean isFolding;
    protected boolean isCalling;
    protected boolean isRaising;
    protected int raiseAmount;
    
    abstract public void decideDiscarding();
    
    public boolean isDiscarding(){
        return this.discardindices.size() > 0;
    }
    
    public PlayingCard discardCard(PlayingCard replacement){
        int discardIndex = this.discardindices.remove(0);
        return this.hand.discardCard(discardIndex, replacement);
    }
    
    abstract public void decideStrategy(Round round);
    
    abstract public int getOpeningBet();
    
    public boolean canOpen(){
    	return this.hand.getGameValue() > OnePair.ONE_PAIR_DEFAULT;
    }
    
    public boolean isFolding(){
    	return this.isFolding;
    }
    
    public boolean isCalling(){
    	return this.isCalling;
    }
    
    public boolean isRaising(){
    	return this.isRaising;
    }
    
    public int getRaise(){
    	return this.raiseAmount;
    }

	public void setHand(Hand hand) {
	    this.hand = hand;
	}

	public Hand getHand(){
		return this.hand;
	}
}
