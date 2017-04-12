package player.framework;

import round.framework.Round;

public interface Player {
    public void decideStrategy(Round round);
    
    public boolean isFolding();
    
    public boolean isCalling();
    
    public boolean isRaising();
    
    public int getRaise();
}
