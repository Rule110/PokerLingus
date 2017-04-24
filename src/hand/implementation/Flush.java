package hand.implementation;

/**
 * Flush Utility class
 * @author Rory Buckley
 * @author Darragh Fay
 *
 */
public class Flush {
	public static final int FLUSH_DEFAULT           = 5000000;//5108;
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
		gVal += FLUSH_DEFAULT;
		return gVal;
	}

    /**
     * Determines if the hand is a Flush
     * @return true if the hand is a flush of all the same suit
     */
    public static boolean isType(PlayingCard[] hand){
        boolean flush = HandUtils.containsFlush(hand);
        boolean straight = HandUtils.containsStraight(hand);
        boolean royal = HandUtils.containsRoyalStraight(hand);
        return flush && !straight && !royal;
    }
}
