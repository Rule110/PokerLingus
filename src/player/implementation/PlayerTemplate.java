package player.implementation;

import hand.framework.Hand;
import player.framework.Player;
import pokerfaice.exceptions.TemplateClassInstantiationException;
import round.framework.Round;

public class PlayerTemplate implements Player {
    protected Hand hand;
    protected boolean isFolding;
    protected boolean isCalling;
    protected boolean isRaising;
    protected int raiseAmount;
    
    public void decideStrategy(Round round){
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
