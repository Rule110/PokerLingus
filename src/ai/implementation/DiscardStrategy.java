package ai.implementation;

import hand.framework.Hand;

import java.util.Vector;

public class DiscardStrategy {
    private Vector<Integer> discardableCards;
    
    DiscardStrategy(Hand hand){
        
    }
    
    public Vector<Integer> getDiscardableCards(){
        return this.discardableCards;
    }
}
