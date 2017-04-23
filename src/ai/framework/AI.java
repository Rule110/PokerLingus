package ai.framework;

import player.implementation.RoundState;
import ai.implementation.DiscardStrategy;
import ai.implementation.Strategy;

import hand.framework.Hand;

public interface AI {
    public boolean decideOpening(Hand hand);
    
    public DiscardStrategy decideDiscarding(Hand hand);
    
    public Strategy decideStrategy(Hand hand, RoundState roundState);
}
