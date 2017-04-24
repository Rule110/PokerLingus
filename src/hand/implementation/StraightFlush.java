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
}
