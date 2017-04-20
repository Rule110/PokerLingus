package hand.implementation;

public class TwoPair {
	public static final int TWO_PAIR_DEFAULT        = 2000000;
	public static int getGameValue(PlayingCard[] hand, int max){
		int gVal = 0;
		for(int i = 0; i < max - 1; i++){
			if(hand[i].getGameVal() != hand[i + 1].getGameVal()){
				gVal += hand[i].getGameVal();
			}
			else{
				i++;
			}
		}
		gVal += (hand[1].getGameVal() * Math.pow(16, 2));
		gVal += (hand[3].getGameVal() * 16);
		gVal += TWO_PAIR_DEFAULT;
		return gVal;
	}
}
