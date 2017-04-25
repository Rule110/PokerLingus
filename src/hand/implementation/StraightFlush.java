package hand.implementation;

/**
 * Straight Flush Utility class
 * @author Rory Buckley
 * @author Darragh Fay
 *
 */
public class StraightFlush {
	public static int STRAIGHT_FLUSH_DEFAULT  = 8000000;//36
	public static int getGameValue(PlayingCard hand[], int max){
		int gVal = 0;
		//The value of a straight flush can be determined using its constant and the value of the highest card.
		if(hand[0].getGameVal() < 14){
			gVal += hand[0].getGameVal();
		}else{
			gVal += hand[1].getGameVal();
		}
		gVal += STRAIGHT_FLUSH_DEFAULT;
		return gVal;
	}
	
    /**
     * Determines if the hand is a Straight Flush
     * @return true if a straight and a flush
     */
    public static boolean isType(PlayingCard[] hand){
        boolean flush = HandUtils.containsFlush(hand);
        boolean straight = HandUtils.containsStraight(hand);
        return straight && flush;
    }
    
    /**
     * Distance of card from being part of HandType StraightFlush
     * @param cardposition
     * @return distance
     */
    double distanceToStraightFlush(int cardposition, PlayingCard[] hand){
      int straightflushcount = 1;
      boolean isstraightflush = true;
      int i = cardposition + 1;
      while (i < hand.length && isstraightflush){
        int thisvalue = hand[i - 1].getFaceVal();
        int nextvalue = hand[i].getFaceVal();

        char thissuit = hand[i - 1].getSuit();
        char nextsuit = hand[i].getSuit();
        
        if (nextvalue + 1 != thisvalue || nextsuit != thissuit){
          isstraightflush = false;
        }
        else {
          straightflushcount++;
          i++;
        }
      }
      isstraightflush = true;
      i = cardposition - 1;
      while (i >= 0 && isstraightflush){
        int thisvalue = hand[i + 1].getFaceVal();
        int previousvalue = hand[i].getFaceVal();

        char thissuit = hand[i + 1].getSuit();
        char previoussuit = hand[i].getSuit();
        
        if (previousvalue - 1 != thisvalue || previoussuit != thissuit){
          isstraightflush = false;
        }
        else {
          straightflushcount++;
          i--;
        }
      }
      return (hand.length - straightflushcount) / 5.0;
    }
}
