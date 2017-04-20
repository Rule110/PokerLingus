package hand.implementation;

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
}
