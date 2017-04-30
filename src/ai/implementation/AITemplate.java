package ai.implementation;

import player.implementation.RoundState;
import game.framework.Game;
import hand.framework.Hand;
import ai.framework.AI;

/**
 * AI Template class
 * Encapsulates code shared between all implementations
 * @author Rory Buckley
 *
 */
abstract public class AITemplate implements AI {
    protected Personality personality;
    protected String name;
    protected Game game;
    
    AITemplate(Game game, String playerID, Personality personality){
        this.game = game;
        this.name = playerID;
        this.personality = personality;
    }
    
    /**
     * AI asked to decide the Opening Bet
     * @return
     */
    public Integer decideOpening(Hand hand, RoundState roundState){
        Integer opening = roundState.getChips() / 10;
        Scale confidence = AIAssessor.assessHand(hand);
        Scale bluffedConfidence = this.personality.getBluffedConfidence(confidence);
        return bluffedConfidence.scaleThat(opening);
    }
    
    /**
     * AI asked to decide Discard Strategy based on Hand
     * @param hand
     * @return discardStrategy
     */
    public DiscardStrategy decideDiscarding(Hand hand){
        
        return AIAssessor.assessDiscarding(hand);
    }
    
    /**
     * AI asked to decide betting Strategy based on Hand and roundState
     * @param hand
     * @param roundState
     * @return strategy
     */
    public Strategy decideStrategy(Hand hand, RoundState roundState){
        
        Scale confidence = AIAssessor.assessHand(hand);
        Scale risk = AIAssessor.assessRisk(roundState);
        Scale reward = AIAssessor.assessReward(roundState);
        Integer raisePool = roundState.getChips() - roundState.getCallValue();
        Strategy strategy = new Strategy(confidence, risk, reward, this.personality, raisePool);
        
        this.expressPersonality(strategy);
        
        return strategy;
    }
    
    /**
     * AI has the ability to express Personality
     * May or may not be used by child classes
     * Simple AI doesn't express Personality
     * Complex AI expresses Personality
     * @param strategy
     */
    abstract protected void expressPersonality(Strategy strategy);
}
