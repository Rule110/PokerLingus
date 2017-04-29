package hand.implementation;

/**
 * Royal Flush Utility class
 * @author Rory Buckley
 * @author Darragh Fay
 *
 */
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
    
    /**
     * Distance of card from being part of HandType RoyalFlush
     * @param cardposition
     * @return
     */
    static double distanceToRoyalFlush(int cardposition, PlayingCard[] hand){
      boolean isroyalflush = hand[cardposition].getGameVal() > 9;
      
      int royalflushcount = isroyalflush ? 1 : 0;
      royalflushcount = hand[4].getGameVal() == 14 ? royalflushcount + 1 : royalflushcount;
      
      int i = cardposition + 1;
      while (i < hand.length && isroyalflush){
        int thisvalue = hand[i - 1].getGameVal();
        int nextvalue = hand[i].getGameVal();

        char thissuit = hand[i - 1].getSuit();
        char nextsuit = hand[i].getSuit();
        
        if (nextvalue < 10 || nextvalue + 1 != thisvalue || nextsuit != thissuit){
          isroyalflush = false;
        }
        else {
          royalflushcount++;
          i++;
        }
      }
      isroyalflush = hand[cardposition].getGameVal() > 9;
      i = cardposition - 1;
      while (i >= 0 && isroyalflush){
        int thisvalue = hand[i + 1].getGameVal();
        int previousvalue = hand[i].getGameVal();

        char thissuit = hand[i + 1].getSuit();
        char previoussuit = hand[i].getSuit();
        
        if (previousvalue < 10 || previousvalue - 1 != thisvalue || previoussuit != thissuit){
          isroyalflush = false;
        }
        else {
          royalflushcount++;
          i--;
        }
      }
      return (hand.length - royalflushcount) / 5.0;
    }
}
