package hand.framework;

import hand.implementation.PlayingCard;

/**
 * Interface for Hand module
 * @author Rory Buckley
 *
 */
public interface Hand extends Iterable<PlayingCard> {
    public boolean isHighHand();
    
    public boolean isOnePair();
    
    public boolean isTwoPair();
    
    public boolean isThreeOfAKind();
    
    public boolean isStraight();
    
    public boolean isFlush();
    
    public boolean isFullHouse();
    
    public boolean isFourOfAKind();
    
    public boolean isStraightFlush();
    
    public boolean isRoyalFlush();
    
    public int getGameValue();
    
    public int getDiscardProbability(int index);
    
    public PlayingCard discardCard(int index, PlayingCard replacement);
    
    public void setCards(PlayingCard card, int i);
    
    public int size();
}
