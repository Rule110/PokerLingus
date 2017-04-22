package bank.implementation;

import java.util.Map;
import java.util.Set;

import bank.framework.Bank;

public class DrawPokerBank implements Bank {
	
	private Map<String, BankAccount> accounts;
	
	public DrawPokerBank(Set<String> playerIDs, int startChips){
    	for (String playerID: playerIDs){
    		accounts.put(playerID, new BankAccount(startChips));
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
