package game.implementation;

import java.util.Map;

import bank.framework.Bank;
import bank.framework.BankFactory;
import network.framework.Network;

import player.framework.Player;
import player.framework.PlayerFactory;
import dealer.framework.Dealer;
import dealer.framework.DealerFactory;
import game.framework.Game;

public class DrawPokerGame implements Game {
    private Dealer dealer;
    private Map<String, Player> players;
    private Bank bank;
    private static int startChips = 10;
    
    public DrawPokerGame(String username, Network network){
        this.dealer = DealerFactory.getDealer("DrawPoker");
        
        for (int i = 0; i < 4; i++){
            this.players.put("AI" + i, PlayerFactory.getPlayer("Automated", network));
        }
        this.players.put(username, PlayerFactory.getPlayer("Human", network));
        
        this.bank = BankFactory.getBank("DrawPoker", players.keySet(), startChips);
    }
}