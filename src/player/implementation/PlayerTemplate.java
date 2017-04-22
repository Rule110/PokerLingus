package player.implementation;

import java.util.Vector;

import hand.framework.Hand;
import hand.implementation.PlayingCard;
import player.framework.Player;
import pokerfaice.exceptions.TemplateClassInstantiationException;
import round.framework.Round;

public class PlayerTemplate implements Player {
	
    protected Hand hand;
    protected Vector<Integer> discardindices;
    protected boolean isFolding;
    protected boolean isCalling;
    protected boolean isRaising;
    protected int raiseAmount;
    
    public void decideDiscarding(){
        throw new TemplateClassInstantiationException();
    }
    
    public boolean isDiscarding(){
        return this.discardindices.size() > 0;
    }
    
    public PlayingCard discardCard(PlayingCard replacement){
        int discardIndex = this.discardindices.remove(0);
        return this.hand.discardCard(discardIndex, replacement);
    }
    
    public void decideStrategy(Round round){
        throw new TemplateClassInstantiationException();
    }
    
    public int getOpeningBet(){
        throw new TemplateClassInstantiationException();
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
