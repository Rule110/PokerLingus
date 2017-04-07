package round.framework;

import round.implementation.DrawPokerRound;

public class RoundFactory {
    public static Round getRound(String type){
        Round round;
        
        switch (type){
        case "DrawPoker":
            round = new DrawPokerRound();
            break;
        default:
            throw new RuntimeException();
        }
        
        return round;
    }
}
