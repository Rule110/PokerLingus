package hand.implementation;

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
}
