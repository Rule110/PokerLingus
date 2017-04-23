package ai.implementation;

import player.implementation.RoundState;
import game.framework.Game;
import hand.framework.Hand;
import ai.framework.AI;

public class AITemplate implements AI {
    protected Personality personality;
    protected Game game;
    
    AITemplate(Game game){
        this.game = game;
    }
    
    public DiscardStrategy decideDiscarding(Hand hand){
        
        return null;
    }
    
    public Strategy decideStrategy(Hand hand, RoundState roundState){
        
        Scale confidence = HandAssessor.assessHand(hand);
        
        Scale risk = RiskAssessor.assessRisk(roundState);
        
        Scale reward = RewardAssessor.assessReward(roundState);
        
        Integer raisePool = roundState.getChips() - roundState.getCallValue();
        
        Strategy strategy = new Strategy(confidence, risk, reward, this.personality, raisePool);
        return strategy;
    }
}
