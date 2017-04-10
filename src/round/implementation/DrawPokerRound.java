package round.implementation;

import java.util.Map;
import java.util.Vector;

import player.framework.Player;
import bank.framework.Bank;
import dealer.framework.Dealer;

public class DrawPokerRound extends RoundTemplate {
    public DrawPokerRound(Map<String, Player> players, Dealer dealer, Bank bank){
        super(players, dealer, bank);
    }
    
    @Override
    public void beginRound(){
        
    }

    @Override
    protected void dealHands(){
        
    }

    @Override
    protected Vector<String> getOpeningPlayers(){
        
        return null;
    }

    @Override
    protected void beginDiscardPhase(){
        
    }

    @Override
    protected void getDiscardedCards(String playerID){
        
    }

    @Override
    protected void beginBettingPhase(){
        
    }

    @Override
    protected boolean isFolding(String playerID){
        
        return false;
    }

    @Override
    protected int getBet(String playerID, int callValue){
        
        return 0;
    }

    @Override
    public String getWinner(){
        
        return super.winner;
    }

    @Override
    protected void addWinnings(String winner, int potSplit){
        
    }
}
