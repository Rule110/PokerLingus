package ui.framework;

import ui.implementation.GraphicalUI;
import ui.implementation.TextualUI;

public class UIFactory {
    public static UI getUI(String type, String networktype){
        UI ui;
        
        switch (type){
        case "Graphical":
            ui = new GraphicalUI(networktype);
            break;
        case "Textual":
            ui = new TextualUI(networktype);
            break;
        default:
            throw new RuntimeException();
        }
        
        return ui;
    }
}
