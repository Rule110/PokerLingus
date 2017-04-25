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
    
    abstract public void decideDiscarding();
    
    public boolean isDiscarding(){
        return this.discardindices.size() > 0;
    }
    
    public PlayingCard discardCard(PlayingCard replacement){
        int discardIndex = this.discardindices.remove(0);
        return this.hand.discardCard(discardIndex, replacement);
    }
    
    abstract public void decideStrategy(Round round);
    
    abstract public int getOpeningBet(Round round);
    
    public boolean canOpen(){
    	return this.hand.getGameValue() > OnePair.ONE_PAIR_DEFAULT;
    }
    
    abstract public boolean isFolding();
    
    abstract public boolean isCalling();
    
    abstract public boolean isRaising();
    
    abstract public int getRaise();

	public void setHand(Hand hand) {
	    this.hand = hand;
	}

	public Hand getHand(){
		return this.hand;
	}
}
