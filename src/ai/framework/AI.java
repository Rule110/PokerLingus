package ai.framework;

import round.framework.Round;

public interface AI {
    public void decideStrategy(Round round);
    
    public boolean isFolding();
    
    public boolean isCalling();
    
    public boolean isRaising();
    
    public int getRaise();

}
