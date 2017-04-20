package hand.implementation;

public class FourOfAKind {
	public static final int FOUR_OF_A_KIND_DEFAULT  = 7000000;//624
	public static int getGameValue(PlayingCard[] hand, int max){
		int gVal = 0;
		/*
		 *  Unique card game values are treated as hexadecimal digits
		 * which are then converted to decimal and added to the type
		 * constant to get the hand value.(The card that appears 4 times
		 * is the highest digit)
		 */
		if(hand[0].getGameVal() == hand[1].getGameVal()){
			gVal += (hand[0].getGameVal() * 16) + (hand[max - 1].getGameVal());
		}else{
			gVal += (hand[max - 1].getGameVal() * 16) + (hand[0].getGameVal());
		}
		gVal += FOUR_OF_A_KIND_DEFAULT;
		return gVal;
	}
}
