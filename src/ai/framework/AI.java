package ai.framework;

import player.implementation.RoundState;
import ai.implementation.DiscardStrategy;
import ai.implementation.Strategy;

import hand.framework.Hand;

public interface AI {
    public DiscardStrategy decideDiscarding(Hand hand);
    
    public Integer decideOpening(Hand hand, RoundState roundState);
    
    public Strategy decideStrategy(Hand hand, RoundState roundState);
}
