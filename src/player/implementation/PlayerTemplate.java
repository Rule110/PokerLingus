package player.implementation;

import player.framework.Player;
import pokerfaice.exceptions.TemplateClassInstantiationException;
import round.framework.RoundState;

public class PlayerTemplate implements Player {
    private boolean isFolding;
    private boolean isCalling;
    private boolean isRaising;
    private int raiseAmount;
    
    public void decideStrategy(RoundState info){
        throw new TemplateClassInstantiationException();
    }
    
    public boolean isFolding(){
        return isFolding;
    }
    
    public boolean isCalling(){
        return isCalling;
    }
    
    public boolean isRaising(){
        return isRaising;
    }
    
    public int getRaise(){
        return raiseAmount;
    }
}
