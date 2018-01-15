package bank.implementation;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import bank.exceptions.BankAccountNotFoundException;
import bank.framework.Bank;

/**
 * This class models a Bank that contains bank accounts
 * for a set of users which are stored in a map.
 */
public class DrawPokerBank implements Bank {
	
	/**
	 * Map of player bank accounts
	 */
	private Map<String, BankAccount> accounts;
	
	/**
	 * Constructor sets up bank accounts for parameterized set of players,
	 * players are assigned an initial balance based off the parameter startChips
	 * @param playerIDs
	 * @param startChips
	 */
	public DrawPokerBank(Set<String> playerIDs, int startChips){
		accounts = new HashMap<String, BankAccount>(playerIDs.size());
    	for (String playerID: playerIDs){
    		System.out.println(playerID);
    		accounts.put(playerID, new BankAccount(startChips));
    	}
	}   
    
	/**
	 * Method returns the amount of funds in the bank account of the player
	 * specified by the player ID
	 * @param String playerID
	 */
    public synchronized int getAvailableFunds(String playerID){
    	if(accounts.get(playerID) == null){
    		System.out.println(playerID);
    		throw new BankAccountNotFoundException();
    	}
        return this.accounts.get(playerID).getAvailableFunds();
    }
    
    /**
     * Method removes an integer amount from the account of the player specified by playerId
     * @param playerID
     * @param withdrawal
     */
    public synchronized void withdraw(String playerID, int withdrawal){
    	if(accounts.get(playerID) == null){
    		throw new BankAccountNotFoundException();
    	}
        this.accounts.get(playerID).withdraw(withdrawal);
    }
    
    /**
     * Method adds an integer amount from the account of the player specified by playerId
     * @param playerID
     * @param deposit
     */
    public synchronized void deposit(String playerID, int deposit){
    	if(accounts.get(playerID) == null){
    		throw new BankAccountNotFoundException();
    	}
        this.accounts.get(playerID).deposit(deposit);
    }
}
