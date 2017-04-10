package player.framework;

import round.framework.RoundState;

public interface Player {
    public void decideStrategy(RoundState info);
    
    public boolean isFolding();
    
    public boolean isCalling();
    
    public boolean isRaising();
    
    public int getRaise();
}
