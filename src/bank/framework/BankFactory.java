package bank.framework;

import bank.implementation.DrawPokerBank;

public class BankFactory {
    public static Bank getBank(String type){
        Bank bank;
        
        switch (type){
        case "DrawPoker":
            bank = new DrawPokerBank(null, 0);
            break;
        default:
            throw new RuntimeException();
        }
        
        return bank;
    }
}//change ver
