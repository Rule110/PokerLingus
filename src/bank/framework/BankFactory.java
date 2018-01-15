package bank.framework;

import java.util.Set;

import bank.implementation.DrawPokerBank;

public class BankFactory {
    public static Bank getBank(String type, Set<String> playerIDs, int initialBalance){
        Bank bank;
        
        switch (type){
        case "DrawPoker":
            bank = new DrawPokerBank(playerIDs, initialBalance);
            break;
        default:
            throw new RuntimeException();
        }
        
        return bank;
    }
}
