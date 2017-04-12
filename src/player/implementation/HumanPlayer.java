package player.implementation;

import pokerfaice.PokerFAIce;
import round.framework.Round;
import ui.framework.UI;
import ui.framework.UIFactory;

public class HumanPlayer extends PlayerTemplate {
    private UI ui;
    
    public HumanPlayer(){
        this.ui = UIFactory.getUI("Textual", PokerFAIce.networktype);
    }
    
    @Override
    public void decideStrategy(Round round){
        
    }
}
