package hand.implementation;

import java.util.HashMap;

public class HighHand {
	public static final int HIGH_HAND_DEFAULT       = 0;      //1302540 possible hands, value range [17507, 568243]
	public static int getGameValue(PlayingCard[] hand, int max){
		int gVal = 0;
		/*
		 * Hand is evaluated by getting the sum of each card's 
		 * game value raised to the power of MAX less the cards position
		 * and adding it to type constant.
		 */
		for(int i = 0; i < max; i++){
			gVal += Math.pow(hand[i].getGameVal(),(max - i));
		}
		gVal += HIGH_HAND_DEFAULT;
		return gVal;
	}
    
    /**
     * Determines if the hand is a High Hand
     * @return true if the highest count is 1 and not a flush
     */
	public static boolean isType(PlayingCard[] hand){
	    HashMap<Integer, Integer> facecount = HandUtils.faceCount(hand);
	    Integer maxcount = HandUtils.maxCount(facecount);
	    boolean flush = HandUtils.containsFlush(hand);
	    boolean straight = HandUtils.containsStraight(hand);
	    return (maxcount == 1) && !straight && !flush;
	}
}
