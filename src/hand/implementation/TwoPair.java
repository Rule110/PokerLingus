package hand.implementation;

import java.util.HashMap;

public class TwoPair {
	public static final int TWO_PAIR_DEFAULT        = 2000000;
	public static int getGameValue(PlayingCard[] hand, int max){
		int gVal = 0;
		for(int i = 0; i < max - 1; i++){
			if(hand[i].getGameVal() != hand[i + 1].getGameVal()){
				gVal += hand[i].getGameVal();
			}
			else{
				i++;
			}
		}
		gVal += (hand[1].getGameVal() * Math.pow(16, 2));
		gVal += (hand[3].getGameVal() * 16);
		gVal += TWO_PAIR_DEFAULT;
		return gVal;
	}

    /**
     * Determines if the hand is a Two Pair
     * @return true if the highest count is a pair and there's two pairs and its not a flush
     */
    public static boolean isType(PlayingCard[] hand){
        HashMap<Integer, Integer> facecount = HandUtils.faceCount(hand);
        Integer maxcount = HandUtils.maxCount(facecount);
        Integer paircount = HandUtils.pairCount(facecount);
        boolean flush = HandUtils.containsFlush(hand);
        return (maxcount == 2) && (paircount == 2) && !flush;
    }
}
