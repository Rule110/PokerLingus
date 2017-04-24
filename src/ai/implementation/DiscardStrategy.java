package ai.implementation;

import hand.framework.Hand;

import java.util.Vector;

/**
 * Discard Strategy class
 * Encapsulates the AI discard strategy and methods to formulate it
 * @author Rory Buckley
 *
 */
public class DiscardStrategy {
    private Vector<Integer> discardableCards;
    
    DiscardStrategy(Hand hand){
        
    }
    
    public Vector<Integer> getDiscardableCards(){
        return this.discardableCards;
    }
}
