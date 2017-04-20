package hand.implementation;

public class Straight {
	public static final int STRAIGHT_DEFAULT        = 4000000;//10200;
	public static int getGameValue(PlayingCard hand[], int max){
		int gVal = 0;
		//The value of a straight flush can be determined using its constant and the value of the highest card.
		//Or the value of the second card if the first is an ace.
		if(hand[0].getGameVal() < 14){
			gVal += hand[0].getGameVal();
		}else{
			gVal += hand[1].getGameVal();
		}
		gVal += STRAIGHT_DEFAULT;
		return gVal;
	}
}
