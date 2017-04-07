package ui.framework;

import ui.implementation.GraphicalUI;
import ui.implementation.TextualUI;

public class UIFactory {
    public static UI getUI(String type){
        UI ui;
        
        switch (type){
        case "Graphical":
            ui = new GraphicalUI();
            break;
        case "Textual":
            ui = new TextualUI();
            break;
        default:
            throw new RuntimeException();
        }
        
        return ui;
    }
}
