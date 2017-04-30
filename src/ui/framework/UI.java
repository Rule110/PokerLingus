package ui.framework;

import java.util.Vector;

import hand.framework.Hand;
import hand.implementation.PlayingCard;
import round.framework.Round;

public interface UI {
    public void decideStrategy(Hand hand, Round round);
    
    public boolean isFolding();
    
    public boolean isCalling();
    
    public boolean isRaising();
    
    public int getRaise();
    
    public boolean isDiscarding();

	public Vector<Integer> decideDiscarding();

	public void checkHand(Hand hand);
}
