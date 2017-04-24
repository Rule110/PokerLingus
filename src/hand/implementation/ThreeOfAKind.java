package hand.implementation;

import java.util.HashMap;

public class ThreeOfAKind {
	public static final int THREE_OF_A_KIND_DEFAULT = 3000000;//54912;
	public static int getGameValue(PlayingCard[] hand, int max){
		int gVal = 0;
		/* 
		 * Unique card game values are treated as hexadecimal digits
		 * which are then converted to decimal and added to the type
		 * constant to get the hand value.(The card that appears 3 times
		 * is the highest digit and the following digits are determined by
		 * card game value in descending order)
		 */
		for(int i = 0, j = 1; i < max; i ++){
			if(hand[i].getGameVal() != hand[max / 2].getGameVal()){
				gVal += hand[i].getGameVal() * Math.pow(16, j);
				j--;
			}
		}
		gVal += (hand[max / 2].getGameVal() * (Math.pow(16, 2)));
		gVal += THREE_OF_A_KIND_DEFAULT;
		return gVal;
	}

    /**
     * Determines if the hand is Three of a Kind
     * @return true if the highest count is three and there's no pairs and its not a flush
     */
    public static boolean isType(PlayingCard[] hand){
        HashMap<Integer, Integer> facecount = HandUtils.faceCount(hand);
        Integer maxcount = HandUtils.maxCount(facecount);
        Integer paircount = HandUtils.pairCount(facecount);
        boolean flush = HandUtils.containsFlush(hand);
        return (maxcount == 3) && (paircount == 0) && !flush;
    }
}
