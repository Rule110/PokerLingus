package hand.implementation;

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
}
