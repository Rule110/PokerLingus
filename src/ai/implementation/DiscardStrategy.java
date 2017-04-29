package ai.implementation;

import java.util.Vector;

/**
 * Discard Strategy class
 * Encapsulates the AI discard strategy and methods to formulate it
 * @author Rory Buckley
 *
 */
public class DiscardStrategy {
    private Vector<Integer> discardableCards;
    
    public DiscardStrategy(){
        this.discardableCards = new Vector<Integer>();
    }
    
    public void addCardToDiscard(Integer handIndex){
        this.discardableCards.add(handIndex);
    }
    
    public Vector<Integer> getDiscardableCards(){
        return this.discardableCards;
    }
}
