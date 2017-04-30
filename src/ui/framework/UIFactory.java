package ui.framework;

import game.framework.Game;
import ui.implementation.GraphicalUI;
import ui.implementation.TextualUI;

public class UIFactory {
    public static UI getUI(String type, Game game, String ID){
        UI ui;
        
        switch (type){
        case "Graphical":
            ui = new GraphicalUI(game, ID);
            break;
        case "Textual":
            ui = new TextualUI(game, ID);
            break;
        default:
            throw new RuntimeException();
        }
        
        return ui;
    }
}
