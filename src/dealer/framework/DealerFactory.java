package dealer.framework;

import dealer.implementation.DrawPokerDealer;

public class DealerFactory {
    public static Dealer getDealer(String type){
        Dealer dealer;
        
        switch (type){
        case "DrawPoker":
            dealer = new DrawPokerDealer();
            break;
        default:
            throw new RuntimeException();
        }
        
        return dealer;
    }
}
