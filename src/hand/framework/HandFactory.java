package hand.framework;

import hand.implementation.DrawPokerHand;

/**
 * Factory for Hand implementations depending on gametype
 * @author Rory Buckley
 *
 */
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
