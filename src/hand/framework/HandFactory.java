package hand.framework;

import hand.implementation.DrawPokerHand;

public class HandFactory {
    public static Hand getHand(String type){
        Hand hand;
        
        switch (type){
        case "DrawPoker":
            hand = new DrawPokerHand();
            break;
        default:
            throw new RuntimeException();
        }
        
        return hand;
    }
}
