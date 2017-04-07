package game.implementation;

import java.util.Map;

import player.framework.Player;
import player.framework.PlayerFactory;
import dealer.framework.Dealer;
import dealer.framework.DealerFactory;
import game.framework.Game;

public class DrawPokerGame implements Game {
    Dealer dealer;
    Map<String, Player> players;
    
    public DrawPokerGame(String username){
        this.dealer = DealerFactory.getDealer("DrawPoker");
        
        for (int i = 0; i < 4; i++){
            this.players.put("AI" + i, PlayerFactory.getPlayer("Automated"));
        }
        this.players.put(username, PlayerFactory.getPlayer("Human"));
    }
}
