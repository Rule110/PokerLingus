package game.framework;

import game.implementation.DrawPokerGame;

public class GameFactory {
    public static Game getGame(String type, String username){
        Game game;
        
        switch (type){
        case "DrawPoker":
            game = new DrawPokerGame(username);
            break;
        default:
            throw new RuntimeException();
        }
        
        return game;
    }
}
