package ai.framework;

import round.framework.RoundState;

public interface AI {
    public void decideStrategy(RoundState info);
    
    public boolean isFolding();
    
    public boolean isCalling();
    
    public boolean isRaising();
    
    public int getRaise();

}
