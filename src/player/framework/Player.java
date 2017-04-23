package player.framework;

import hand.framework.Hand;
import hand.implementation.PlayingCard;
import round.framework.Round;

public interface Player {
    public void decideDiscarding();
    
    public boolean isDiscarding();
    
    public PlayingCard discardCard(PlayingCard replacement);
    
    public void decideStrategy(Round round);
    
    public int getOpeningBet();
    
    public boolean canOpen();
    
    public boolean isFolding();
    
    public boolean isCalling();
    
    public boolean isRaising();
    
    public int getRaise();
    
    public Hand getHand();
    
    public void setHand(Hand hand);
}
