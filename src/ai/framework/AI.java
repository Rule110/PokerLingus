package ai.framework;

import hand.framework.Hand;
import round.framework.Round;

public interface AI {
    public void decideStrategy(Hand hand, Round round);
    
    public boolean isFolding();
    
    public boolean isCalling();
    
    public boolean isRaising();
    
    public int getRaise();

}
