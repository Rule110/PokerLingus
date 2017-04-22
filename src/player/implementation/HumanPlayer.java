package player.implementation;

import game.framework.Game;
import round.framework.Round;
import ui.framework.UI;
import ui.framework.UIFactory;

public class HumanPlayer extends PlayerTemplate {
    private UI ui;
    
    public HumanPlayer(Game game){
        this.ui = UIFactory.getUI("Textual", game);
    }
    
    public void decideDiscarding(){
        
    }
    
    public int getOpeningBet(){
        
        return 0;
    }
    
    public void decideStrategy(Round round){
        this.ui.decideStrategy(super.hand, round);
    }
}
