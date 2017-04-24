package hand.implementation;

import java.util.HashMap;

/**
 * One Pair Utility class
 * @author Rory Buckley
 * @author Darragh Fay
 *
 */
public class OnePair {
	public static final int ONE_PAIR_DEFAULT        = 1000000;//1098240;
	public static int getGameValue(PlayingCard[] hand, int max){
		int gVal = 0;
		/* 
		 * Unique card game values are treated as hexadecimal digits
		 * which are then converted to decimal and added to the type
		 * constant to get the hand value.(The card that appears in the pair
		 * is the highest digit and the following digits are determined by
		 * card game value in descending order)
		 */
		for(int i = 0, j = 2; i < max - 1; i++){
			if(hand[i].getGameVal() != hand[i + 1].getGameVal()){
				gVal += hand[i].getGameVal() * Math.pow(16, j);
				j--;
			}else{
				gVal += hand[i].getGameVal() * Math.pow(16, 3);
				i++;
			}
		}
		//Allows for case where final card isn't in pair.
		if(hand[max - 2].getGameVal() != hand[max - 1].getGameVal()){
			gVal += hand[max - 1].getGameVal();
		}

		gVal += ONE_PAIR_DEFAULT;
		return gVal;
	}

    /**
     * Determines if the hand is a One Pair
     * @return true if the highest count is a pair and there's one pair and its not a flush
     */
    public static boolean isType(PlayingCard[] hand){
        HashMap<Integer, Integer> facecount = HandUtils.faceCount(hand);
        Integer maxcount = HandUtils.maxCount(facecount);
        Integer paircount = HandUtils.pairCount(facecount);
        boolean flush = HandUtils.containsFlush(hand);
        return (maxcount == 2) && (paircount == 1) && !flush;
    }
}
