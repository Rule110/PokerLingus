package round.implementation;

import java.util.Map;
import java.util.Vector;

import bank.framework.Bank;
import bank.implementation.DrawPokerBank;
import dealer.framework.Dealer;

import player.framework.Player;
import pokerfaice.exceptions.TemplateClassInstantiationException;
import pot.framework.Pot;
import round.framework.Round;

public class RoundTemplate implements Round {
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
    
    public void beginRound(){
        throw new TemplateClassInstantiationException();
    }
    
    protected void dealHands(){
    	// need Jack
        throw new TemplateClassInstantiationException();
    }
    
    protected Vector<String> getOpeningPlayers(){
    	// returns the names of the players that can open
    	// as those will be compared to all players that are playing
    	
    	Vector<String> openingPlayers = new Vector<String>();  	
    	// check the hands of each player and see if they have a pair or higher
    	// if they do add them to the vector
    	
        throw new TemplateClassInstantiationException();
    }
    
    protected void beginDiscardPhase(){
        throw new TemplateClassInstantiationException();
    }
    
    protected void getDiscardedCards(String playerID){
        throw new TemplateClassInstantiationException();
    }
    
    protected void beginBettingPhase(){
        throw new TemplateClassInstantiationException();
    }
    
    protected boolean isFolding(String playerID){
        throw new TemplateClassInstantiationException();
    }
    
    protected int getBet(String playerID, int callValue){
        throw new TemplateClassInstantiationException();
    }
    
    public int getPotValue(){
        return this.pot.getTotalValue();
    }
    
    public int getAvailableFunds(String playerID){
        return this.bank.getAvailableFunds(playerID);
    }
    
    public String getWinner(){
        throw new TemplateClassInstantiationException();
    }
    
    protected void addWinnings(String winner, int potSplit){ 
    	bank.deposit(winner, potSplit);
    	throw new TemplateClassInstantiationException();
        
    }
}
