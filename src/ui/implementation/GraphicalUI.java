package ui.implementation;

import java.util.Vector;

import game.framework.Game;
import hand.framework.Hand;
import round.framework.Round;

public class GraphicalUI extends UITemplate {
    public GraphicalUI(Game game){
        super(game);
    }
    
    public void decideStrategy(Hand hand, Round round){
        
    }

	@Override
	public Vector<Integer> decideDiscarding() {
		// TODO Auto-generated method stub
		return null;
	}
}
