package hand.implementation;

/**
 * Straight Utility class
 * @author Rory Buckley
 * @author Darragh Fay
 *
 */
public class Straight {
	public static final int STRAIGHT_DEFAULT        = 4000000;//10200;
	public static int getGameValue(PlayingCard hand[], int max){
		int gVal = 0;
		//The value of a straight flush can be determined using its constant and the value of the highest card.
		//Or the value of the second card if the first is an ace.
		if(hand[0].getGameVal() < 14){
			gVal += hand[0].getGameVal();
		}else{
			gVal += hand[1].getGameVal();
		}
		gVal += STRAIGHT_DEFAULT;
		return gVal;
	}

    /**
     * Determines if the hand is a Straight
     * @return true if the hand has a straight
     */
    public static boolean isType(PlayingCard[] hand){
        boolean flush = HandUtils.containsFlush(hand);
        boolean straight = HandUtils.containsStraight(hand);
        return straight && !flush;
    }
    
    /**
     * Distance of card from being part of HandType Straight
     * @param cardposition
     * @return distance
     */
    static double distanceToStraight(int cardposition, PlayingCard[] hand){
      int straightcount = 1;
      boolean isstraight = true;
      int i = cardposition + 1;
      while (i < hand.length && isstraight){
        int thisvalue = hand[i - 1].getFaceVal();
        int nextvalue = hand[i].getFaceVal();
        
        if (nextvalue + 1 != thisvalue){
          isstraight = false;
        }
        else {
          straightcount++;
          i++;
        }
      }
      isstraight = true;
      i = cardposition - 1;
      while (i >= 0 && isstraight){
        int thisvalue = hand[i + 1].getFaceVal();
        int previousvalue = hand[i].getFaceVal();
        
        if (previousvalue - 1 != thisvalue){
          isstraight = false;
        }
        else {
          straightcount++;
          i--;
        }
      }
      return (hand.length - straightcount) / 5.0;
    }
}
