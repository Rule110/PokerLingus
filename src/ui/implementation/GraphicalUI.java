package ui.implementation;

import java.util.Vector;

import game.framework.Game;
import hand.framework.Hand;
import hand.implementation.PlayingCard;
import round.framework.Round;

public class GraphicalUI extends UITemplate {
    public GraphicalUI(Game game, String ID){
        super(game, ID);
    }
    
    public void decideStrategy(Hand hand, Round round){
        
    }

	@Override
	public Vector<Integer> decideDiscarding() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void checkHand(int chips, Hand hand) {
		// TODO Auto-generated method stub
		
	}
}
