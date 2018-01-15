package round.framework;

import java.util.Map;

import bank.framework.Bank;

import dealer.framework.Dealer;
import network.framework.Network;
import player.framework.Player;

import round.implementation.DrawPokerRound;

public class RoundFactory {
    public static Round getRound(String type, Map<String, Player> players, Dealer dealer, Bank bank, Network network){
        Round round;
        
        switch (type){
        case "DrawPoker":
            round = new DrawPokerRound(players, dealer, bank, network);
            break;
        default:
            throw new RuntimeException();
        }
        
        return round;
    }
}
