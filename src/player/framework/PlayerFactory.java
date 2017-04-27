package player.framework;

import game.framework.Game;
import player.implementation.AutomatedPlayer;
import player.implementation.HumanPlayer;

public class PlayerFactory {
    public static Player getPlayer(String type, Game game, String ID){
        Player player;
        
        switch (type){
        case "Human":
            player = new HumanPlayer(game, ID);
            break;
        case "Automated":
            player = new AutomatedPlayer(game, ID);
            break;
        default:
            throw new RuntimeException();
        }
        
        return player;
    }
}
