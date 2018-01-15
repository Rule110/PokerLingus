package pot.framework;

import pot.implementation.DrawPokerPot;

public class PotFactory {
    public static Pot getPot(String type){
        Pot pot;
        
        switch (type){
        case "DrawPoker":
            pot = new DrawPokerPot();
            break;
        default:
            throw new RuntimeException();
        }
        
        return pot;
    }
}
