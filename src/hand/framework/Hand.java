package hand.framework;

import hand.implementation.PlayingCard;

public interface Hand {
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
    
    public void discardCard(int index);
    
    public void setCards(PlayingCard card, int i);
}
