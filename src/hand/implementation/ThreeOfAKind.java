package hand.implementation;

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
}
