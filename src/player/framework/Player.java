package player.framework;

import java.util.Vector;

import hand.framework.Hand;
import hand.implementation.PlayingCard;
import round.framework.Round;

/**
 * Interface for Player module
 * @author Rory Buckley
 * @author Darragh Fay
 * @author Ciaran Murphy
 *
 */
public interface Player {
    public boolean canOpen();
    
    public int getOpeningBet(Round round);
    
    public void decideDiscarding();
    
    public boolean isDiscarding();
    
    public PlayingCard discardCard(PlayingCard replacement);
    
    public void decideStrategy(Round round);
    
    public boolean isFolding();
    
    public boolean isCalling();
    
    public boolean isRaising();
    
    public int getRaise();
    
    public Hand getHand();
    
    public void setHand(Hand hand);
}
