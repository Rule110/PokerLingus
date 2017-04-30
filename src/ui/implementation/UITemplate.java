package ui.implementation;

import java.util.Vector;

import game.framework.Game;
import hand.framework.Hand;
import round.framework.Round;
import ui.framework.UI;

abstract public class UITemplate implements UI {
    protected Game game;
    protected boolean isFolding;
    protected boolean isCalling;
    protected boolean isRaising;
    protected boolean isDiscarding;
    protected int raiseAmount;
	protected String ID;
    
    UITemplate(Game game, String ID){
    	this.ID = ID;
        this.game = game;
    }
    
    abstract public void decideStrategy(Hand hand, Round round);
    
    abstract public Vector<Integer> decideDiscarding();
    
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
    
    public boolean isDiscarding(){
    	return isDiscarding;
    }
}
