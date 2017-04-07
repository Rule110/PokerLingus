package player.framework;

import player.implementation.AutomatedPlayer;
import player.implementation.HumanPlayer;

public class PlayerFactory {
    public static Player getPlayer(String type){
        Player player;
        
        switch (type){
        case "Human":
            player = new HumanPlayer();
            break;
        case "Automated":
            player = new AutomatedPlayer();
            break;
        default:
            throw new RuntimeException();
        }
        
        return player;
    }
}
