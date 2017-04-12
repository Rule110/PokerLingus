package player.implementation;

import pokerfaice.PokerFAIce;
import round.framework.RoundState;
import ui.framework.UI;
import ui.framework.UIFactory;

public class HumanPlayer extends PlayerTemplate {
    private UI ui;
    
    public HumanPlayer(){
        this.ui = UIFactory.getUI("Textual", PokerFAIce.networktype);
    }
    
    public void decideStrategy(RoundState info){
        
    }
}
