package ui.framework;

import hand.framework.Hand;
import round.framework.Round;

public interface UI {
    public void decideStrategy(Hand hand, Round round);
    
    public boolean isFolding();
    
    public boolean isCalling();
    
    public boolean isRaising();
    
    public int getRaise();
    
    public boolean isDiscarding();

}
