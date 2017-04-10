package hand.implementation;

import hand.framework.Hand;

public class DrawPokerHand implements Hand {
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
        
        return 0;
    }
    
    public int getDiscardProbability(int index){
        
        return 0;
    }
    
    public void discardCard(int index){
        
    }
}
