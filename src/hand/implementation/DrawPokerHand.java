package hand.implementation;

import hand.framework.Hand;

public class DrawPokerHand implements Hand {
    
	public static int HAND_LIMIT = 5;
	public PlayingCard[] hand;
	
	public DrawPokerHand(){
		hand = new PlayingCard[HAND_LIMIT];
	}
	
	public void setCards(PlayingCard newCard, int i){
		hand[i] = newCard;
	}
	
	public boolean isHighHand(){
        
        return false;
    }
    
    public boolean isOnePair(){
        
        return false;
    }
    
    public boolean isTwoPair(){
        
        return false;
    }
    
    public boolean isThreeOfAKind(){
        
        return false;
    }
    
    public boolean isStraight(){
        
        return false;
    }
    
    public boolean isFlush(){
        
        return false;
    }
    
    public boolean isFullHouse(){
        
        return false;
    }
    
    public boolean isFourOfAKind(){
        
        return false;
    }
    
    public boolean isStraightFlush(){
        
        return false;
    }
    
    public boolean isRoyalFlush(){
        
        return false;
    }
    
    public int getGameValue(){
        
        return OnePair.getGameValue(hand, HAND_LIMIT);
    }
    
    public int getDiscardProbability(int index){
        
        return 0;
    }
    
    public PlayingCard discardCard(int index, PlayingCard replacement){
        
        return null;
    }
}
