package player.framework;

import network.framework.Network;
import player.implementation.AutomatedPlayer;
import player.implementation.HumanPlayer;

public class PlayerFactory {
    public static Player getPlayer(String type, Network network){
        Player player;
        
        switch (type){
        case "Human":
            player = new HumanPlayer(network);
            break;
        case "Automated":
            player = new AutomatedPlayer(network);
            break;
        default:
            throw new RuntimeException();
        }
        
        return player;
    }
}
