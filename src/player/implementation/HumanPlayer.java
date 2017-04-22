package player.implementation;

import network.framework.Network;
import round.framework.Round;
import ui.framework.UI;
import ui.framework.UIFactory;

public class HumanPlayer extends PlayerTemplate {
    private UI ui;
    
    public HumanPlayer(Network network){
        this.ui = UIFactory.getUI("Textual", network);
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
