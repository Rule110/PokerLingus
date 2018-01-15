package hand.implementation;

import java.util.HashMap;

/**
 * Full House Utility class
 * @author Rory Buckley
 * @author Darragh Fay
 *
 */
public class FullHouse {
	public static final int FULL_HOUSE_DEFAULT      = 6000000;//3744
	public static int getGameValue(PlayingCard[] hand, int max){
		int gVal = 0;
		/* 
		 * Unique card game values are treated as hexadecimal digits
		 * which are then converted to decimal and added to the type
		 * constant to get the hand value.(The card that appears 3 times
		 * is the highest digit)
		 */
		int pairVal;
		if(hand[0].getGameVal() == hand[max / 2].getGameVal()){
			pairVal =  hand[max - 1].getGameVal();
		}else{
			pairVal = hand[0].getGameVal();
		}
		gVal += (hand[max / 2].getGameVal() * 16) + pairVal;
		gVal += FULL_HOUSE_DEFAULT;
		return gVal;
	}

    /**
     * Determines if the hand is a Full House
     * @return true if the highest count is 3 and there is also one pair present
     */
    public static boolean isType(PlayingCard[] hand){
        HashMap<Integer, Integer> facecount = HandUtils.faceCount(hand);
        Integer maxcount = HandUtils.maxCount(facecount);
        Integer paircount = HandUtils.pairCount(facecount);
        return (maxcount == 3) && (paircount == 1);
    }
    
    /**
     * Distance of card from being part of HandType FullHouse
     * @param cardposition
     * @return distance
     */
    static double distanceToFullHouse(int cardposition, PlayingCard[] hand){
      return HandUtils.min((HandUtils.distanceOfCardFrom(3, cardposition, hand) 
              + HandUtils.distanceOfRestFrom(2, cardposition, hand)) / 5.0,
          (HandUtils.distanceOfCardFrom(2, cardposition, hand) 
                  + HandUtils.distanceOfRestFrom(3, cardposition, hand)) / 5.0);
    }
}
