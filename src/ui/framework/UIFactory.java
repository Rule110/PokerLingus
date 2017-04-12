package ui.framework;

import network.framework.Network;
import ui.implementation.GraphicalUI;
import ui.implementation.TextualUI;

public class UIFactory {
    public static UI getUI(String type, Network network){
        UI ui;
        
        switch (type){
        case "Graphical":
            ui = new GraphicalUI(network);
            break;
        case "Textual":
            ui = new TextualUI(network);
            break;
        default:
            throw new RuntimeException();
        }
        
        return ui;
    }
}
