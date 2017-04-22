package ui.implementation;

import game.framework.Game;
import hand.framework.Hand;
import round.framework.Round;
import ui.framework.UI;

abstract public class UITemplate implements UI {
    protected Game game;
    protected boolean isFolding;
    protected boolean isCalling;
    protected boolean isRaising;
    protected int raiseAmount;
    
    UITemplate(Game game){
        this.game = game;
    }
    
    abstract public void decideStrategy(Hand hand, Round round);
    
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
