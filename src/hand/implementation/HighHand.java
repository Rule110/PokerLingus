package hand.implementation;

public class HighHand {
	public static final int HIGH_HAND_DEFAULT       = 0;      //1302540 possible hands, value range [17507, 568243]
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
		gVal += HIGH_HAND_DEFAULT;
		return gVal;
	}
}
