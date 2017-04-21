package player.framework;

import hand.framework.Hand;
import hand.implementation.PlayingCard;
import round.framework.Round;

public interface Player {
    public void decideStrategy(Round round);
    
    public boolean isFolding();
    
    public boolean isCalling();
    
    public boolean isRaising();
    
    public int getRaise(int playerChips);
        
    public Hand getHand();
    
    public void setHand(Hand hand);
    
   // public PlayingCard discard();
}
