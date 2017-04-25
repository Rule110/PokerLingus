package hand.implementation;

import java.util.Iterator;

import hand.framework.Hand;

public class DrawPokerHand implements Hand {
    
	public static int HAND_LIMIT = 5;
	private PlayingCard[] hand;
	
	public DrawPokerHand(){
		hand = new PlayingCard[HAND_LIMIT];
	}
	
	public void setCards(PlayingCard newCard, int i){
		hand[i] = newCard;
	}
	
    public boolean isHighHand(){
      return HighHand.isType(this.hand);
    }
    
    public boolean isOnePair(){
      return OnePair.isType(this.hand);
    }
    
    public boolean isTwoPair(){
      return TwoPair.isType(this.hand);
    }
    
    public boolean isThreeOfAKind(){
      return ThreeOfAKind.isType(this.hand);
    }
    
    public boolean isFourOfAKind(){
      return FourOfAKind.isType(this.hand);
    }
    
    public boolean isFlush(){
      return Flush.isType(this.hand);
    }
    
    public boolean isStraight(){
      return Straight.isType(this.hand);
    }
    
    public boolean isStraightFlush(){
      return StraightFlush.isType(this.hand);
    }
    
    public boolean isRoyalFlush(){
      return RoyalFlush.isType(this.hand);
    }
    
    public boolean isFullHouse(){
      return FullHouse.isType(this.hand);
    }
    
    /**
     * ToString method to print out cards in a hand
     */
    @Override
    public String toString(){
      StringBuffer buffer = new StringBuffer();
      for (PlayingCard card : this.hand){
        buffer.append(card + ", ");
      }
      return buffer.toString();
    }
    
    /**
     * Get Game Value of Hand
     * @reutnr gameValue
     */
    public int getGameValue(){
        int gameValue = 0;
        if (this.isHighHand()){
            gameValue = HighHand.getGameValue(this.hand, HAND_LIMIT);
        }
        else if (this.isOnePair()){
            gameValue = OnePair.getGameValue(this.hand, HAND_LIMIT);
        }
        else if (this.isTwoPair()){
            gameValue = TwoPair.getGameValue(this.hand, HAND_LIMIT);
        }
        else if (this.isThreeOfAKind()){
            gameValue = ThreeOfAKind.getGameValue(this.hand, HAND_LIMIT);
        }
        else if (this.isStraight()){
            gameValue = Straight.getGameValue(this.hand, HAND_LIMIT);
        }
        else if (this.isFlush()){
            gameValue = Flush.getGameValue(this.hand, HAND_LIMIT);
        }
        else if (this.isFullHouse()){
            gameValue = FullHouse.getGameValue(this.hand, HAND_LIMIT);
        }
        else if (this.isFourOfAKind()){
            gameValue = FourOfAKind.getGameValue(this.hand, HAND_LIMIT);
        }
        else if (this.isStraightFlush()){
            gameValue = StraightFlush.getGameValue(this.hand, HAND_LIMIT);
        }
        else if (this.isRoyalFlush()){
            gameValue = RoyalFlush.getGameValue();
        }
        return gameValue;
    }
    
    /**
     * Returns a discarded card from the hand and swaps with a replacement
     * @return discardedCard
     */
    public PlayingCard discardCard(int index, PlayingCard replacement){
        PlayingCard discardedCard = this.hand[index];
        this.hand[index] = replacement;
        return discardedCard;
    }
    
    /**
     * Size of Hand is implemented for DrawPokerHand by HAND_LIMIT constant
     */
    public int size(){
        return DrawPokerHand.HAND_LIMIT;
    }
    
    /**
     * Implements ability to Iterate through PlayingCards of Hand from high to low
     */
    public Iterator<PlayingCard> iterator(){
        return new Iterator<PlayingCard>(){
            private int i = 0;
            
            public boolean hasNext(){
                return i < hand.length;
            }
            
            public PlayingCard next(){
                PlayingCard next = null;
                if (this.hasNext()){
                    next = hand[i++];
                }
                return next;
            }
            
        };
    }
}
