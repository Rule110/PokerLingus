package ai.implementation;

import java.util.concurrent.ThreadLocalRandom;

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
    /**
     * Degree of potential reward on scale based on round state
     * @param roundState
     * @return scale of reward
     */
    public static Scale assessReward(RoundState roundState){
        Integer potValue = roundState.getPotValue();
        Integer chips = roundState.getChips();
        Double ratio = (double)potValue / (double)chips;
        Integer scale = (int)(ratio * Scale.MAX_SCALE);
        Scale reward = new Scale(scale - 1);
        return reward;
    }
    
    /**
     * Degree of potential risk on scale based on round state
     * @param roundState
     * @return scale of risk
     */
    public static Scale assessRisk(RoundState roundState){
        Integer chips = roundState.getChips();
        Integer callValue = roundState.getCallValue();
        Double ratio = (double)callValue / (double)chips;
        Integer scale = (int)(ratio * Scale.MAX_SCALE);
        Scale risk = new Scale(scale);
        return risk;
    }
    
    /**
     * Degree of confidence in hand on scale
     * @param hand
     * @return confidence in hand
     */
    public static Scale assessHand(Hand hand){
        Double proportionateValue = (double)hand.getGameValue() / (double)RoyalFlush.ROYAL_FLUSH_DEFAULT;
        Integer valueOnScale = (int)(proportionateValue * Scale.MAX_SCALE);
        Scale handAssessment = new Scale(valueOnScale);
        return handAssessment;
    }
    
    /**
     * Probabilistically chosen cards to discard from hand
     * @param hand
     * @return cards to discard
     */
    public static DiscardStrategy assessDiscarding(Hand hand){
        DiscardStrategy strategy = new DiscardStrategy();
        for (int i = 0; i < hand.size(); i++){
            int random = ThreadLocalRandom.current().nextInt(0, 101);
            if (hand.getDiscardProbability(i) < random){
                strategy.addCardToDiscard(i);
            }
        }
        return strategy;
    }
}
