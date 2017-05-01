package player.implementation;

import game.framework.Game;
import round.framework.Round;
import ui.framework.UI;
import ui.framework.UIFactory;

/**
 * Player implementation using a human player interacting through a UI module
 * @author Cameron Newman
 *
 */
public class HumanPlayer extends PlayerTemplate {
    private UI ui;
    
    public HumanPlayer(Game game, String ID){
    	super.ID = ID;
        this.ui = UIFactory.getUI("Textual", game, ID);
    }
    
    public void decideDiscarding(){
    	discardindices.clear();
    	discardindices = this.ui.decideDiscarding();
    	System.out.println(discardindices);
    }
    
    public boolean isDiscarding(){
    	return discardindices.size() > 0;
    }
    
    public int getOpeningBet(Round round){
        
        return 0;
    }
    
    public void decideStrategy(Round round){
    	this.ui.checkHand(round.getAvailableFunds(ID), super.getHand());
        this.ui.decideStrategy(super.hand, round);
    }
    
    public boolean isFolding(){
        return this.ui.isFolding();
    }
    
    public boolean isCalling(){
        return this.ui.isCalling();
    }
    
    public boolean isRaising(){
        return this.ui.isRaising();
    }
    
    public int getRaise(){
        return this.ui.getRaise();
    }
}
