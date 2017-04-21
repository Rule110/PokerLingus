package bank.implementation;

import java.util.Map;

import bank.framework.Bank;
import player.framework.Player;

public class DrawPokerBank implements Bank {
	
	private Map<String, BankAccount> accounts;
	
	public DrawPokerBank(Map<String, Player> players, int startChips){
    	for (String p: players.keySet()){
    		accounts.put(p, new BankAccount(startChips));
    	}
	}   
    
    public synchronized int getAvailableFunds(String playerID){
        return this.accounts.get(playerID).getAvailableFunds();
    }
    
    public synchronized void withdraw(String playerID, int withdrawal){
        this.accounts.get(playerID).withdraw(withdrawal);
    }
    
    public synchronized void deposit(String playerID, int deposit){
        this.accounts.get(playerID).deposit(deposit);
    }
}
