package ui.framework;

import round.framework.Round;

public interface UI {
    public void decideStrategy(Round round);
    
    public boolean isFolding();
    
    public boolean isCalling();
    
    public boolean isRaising();
    
    public int getRaise();

}
