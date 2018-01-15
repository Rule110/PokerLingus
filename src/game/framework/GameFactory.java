package game.framework;

import network.framework.Network;
import game.implementation.DrawPokerGame;

public class GameFactory {
    public static Game getGame(String type, String username, Network network){
        Game game;
        
        switch (type){
        case "DrawPoker":
            game = new DrawPokerGame(username, network);
            break;
        default:
            throw new RuntimeException();
        }
        
        return game;
    }
}
