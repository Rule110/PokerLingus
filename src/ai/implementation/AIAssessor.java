package ai.implementation;

import hand.framework.Hand;
import hand.implementation.RoyalFlush;
import player.implementation.RoundState;

/**
 * AI Assessor Utility Class
 * Utility class to help AI assess degrees of certainty on scales
 * @author Rory Buckley
 *
 */
public class AIAssessor {
    public static Scale assessReward(RoundState roundState){
        Integer potValue = roundState.getPotValue();
        Integer chips = roundState.getChips();
        Double ratio = (double)potValue / (double)chips;
        Integer scale = (int)(ratio * Scale.MAX_SCALE);
        Scale reward = new Scale(scale);
        return reward;
    }
    
    public static Scale assessRisk(RoundState roundState){
        Integer chips = roundState.getChips();
        Integer callValue = roundState.getCallValue();
        Double ratio = (double)callValue / (double)chips;
        Integer scale = (int)(ratio * Scale.MAX_SCALE);
        Scale risk = new Scale(scale);
        return risk;
    }
    
    public static Scale assessHand(Hand hand){
        Double proportionateValue = (double)hand.getGameValue() / (double)RoyalFlush.ROYAL_FLUSH_DEFAULT;
        Integer valueOnScale = (int)(proportionateValue * Scale.MAX_SCALE);
        Scale handAssessment = new Scale(valueOnScale);
        return handAssessment;
    }
    
    public static DiscardStrategy assessDiscarding(Hand hand){
        
        return null;
    }
}
