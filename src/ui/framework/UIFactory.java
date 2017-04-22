package ui.framework;

import game.framework.Game;
import ui.implementation.GraphicalUI;
import ui.implementation.TextualUI;

public class UIFactory {
    public static UI getUI(String type, Game game){
        UI ui;
        
        switch (type){
        case "Graphical":
            ui = new GraphicalUI(game);
            break;
        case "Textual":
            ui = new TextualUI(game);
            break;
        default:
            throw new RuntimeException();
        }
        
        return ui;
    }
}
