package round.implementation;

import java.util.Map;
import java.util.Vector;

import bank.framework.Bank;

import dealer.framework.Dealer;

import player.framework.Player;
import pot.framework.Pot;
import round.framework.Round;

abstract public class RoundTemplate implements Round {
    protected Map<String, Player> players;
    protected Pot pot;
    protected Dealer dealer;
    protected Bank bank;
    protected String winner;
    
    public RoundTemplate(Map<String, Player> players, Dealer dealer, Bank bank){
        this.players = players;
        this.dealer = dealer;
        this.bank = bank;
    }
    
    abstract public void beginRound();
    
    abstract protected void dealHands();
    
    abstract protected Vector<String> getOpeningPlayers();
    
    abstract protected void beginDiscardPhase();
    
    abstract protected void getDiscardedCards(String playerID);
    
    abstract protected void beginBettingPhase();
    
    abstract protected boolean isFolding(String playerID);
    
    abstract public int getCallValue();
    
    public int getPotValue(){
        return this.pot.getTotalValue();
    }
    
    public int getAvailableFunds(String playerID){
        return this.bank.getAvailableFunds(playerID);
    }
    
    abstract public String getWinner();
    
    abstract protected void addWinnings(String winner, int potSplit);
}
