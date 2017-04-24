package hand.implementation;

public class RoyalFlush {
	public static final int ROYAL_FLUSH_DEFAULT     = 9000000;//4
	public static int getGameValue(){    
	    return ROYAL_FLUSH_DEFAULT;
	}

    /**
     * Determines if the hand is a Royal Flush
     * @return true if a flush and royal
     */
    public static boolean isType(PlayingCard[] hand){
        boolean flush = HandUtils.containsFlush(hand);
        boolean royal = HandUtils.containsRoyalStraight(hand);
        return flush && royal;
    }
}
