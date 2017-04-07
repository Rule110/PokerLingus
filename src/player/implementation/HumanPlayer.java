package player.implementation;

import player.framework.Player;
import ui.framework.UI;
import ui.framework.UIFactory;

public class HumanPlayer implements Player {
    private UI ui;
    
    public HumanPlayer(){
        this.ui = UIFactory.getUI("Textual");
    }//
}
