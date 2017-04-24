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
    
    public int getGameValue(){
        int gamevalue = 0;
        if (this.isHighHand()){
            gamevalue = HighHand.getGameValue(this.hand, HAND_LIMIT);
        }
        else if (this.isOnePair()){
            gamevalue = OnePair.getGameValue(this.hand, HAND_LIMIT);
        }
        else if (this.isTwoPair()){
            gamevalue = TwoPair.getGameValue(this.hand, HAND_LIMIT);
        }
        else if (this.isThreeOfAKind()){
            gamevalue = ThreeOfAKind.getGameValue(this.hand, HAND_LIMIT);
        }
        else if (this.isStraight()){
            gamevalue = Straight.getGameValue(this.hand, HAND_LIMIT);
        }
        else if (this.isFlush()){
            gamevalue = Flush.getGameValue(this.hand, HAND_LIMIT);
        }
        else if (this.isFullHouse()){
            gamevalue = FullHouse.getGameValue(this.hand, HAND_LIMIT);
        }
        else if (this.isFourOfAKind()){
            gamevalue = FourOfAKind.getGameValue(this.hand, HAND_LIMIT);
        }
        else if (this.isStraightFlush()){
            gamevalue = StraightFlush.getGameValue(this.hand, HAND_LIMIT);
        }
        else if (this.isRoyalFlush()){
            gamevalue = RoyalFlush.getGameValue();
        }
        return gamevalue;
    }
    
    public PlayingCard discardCard(int index, PlayingCard replacement){
        
        return null;
    }
}
