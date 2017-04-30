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
        this.ui = UIFactory.getUI("Textual", game);
    }
    
    public void decideDiscarding(){
    	discardindices.clear();
    	discardindices = this.ui.decideDiscarding();
    }
    
    public boolean isDiscarding(){
    	return this.ui.isDiscarding();
    }
    
    public int getOpeningBet(Round round){
        
        return 0;
    }
    
    public void decideStrategy(Round round){
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
