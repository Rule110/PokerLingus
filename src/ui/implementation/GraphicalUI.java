package ui.implementation;

import java.util.Vector;

import game.framework.Game;
import hand.framework.Hand;
import hand.implementation.PlayingCard;
import round.framework.Round;

/**
 * This class exists for the purpose of extending
 * the implementation to display hand and chip graphics as well as avatar
 * opponents. Currently twitter4J doesn't allow sending of images in direct
 * message but we believe this will be possible in upcoming updates.
 * @author Darragh
 *
 */
public class GraphicalUI extends UITemplate {
	/**
	 * Constructor takes in user identification
	 * and a reference to the game thread the player is a part of.
	 * @param game
	 * @param ID
	 */
    public GraphicalUI(Game game, String ID){
        super(game, ID);
    }
    
    /**
     * Sets booleans which decide the players actions based
     * on signals from the game state
     */
    public void decideStrategy(Hand hand, Round round){
        
    }

    /**
     * This method asks the user if they want to fold and sets a boolean based on their response
     * @return isFolding
     */
	@Override
	public Vector<Integer> decideDiscarding() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void checkHand(int chips, Hand hand) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkHand(Hand hand) {
		// TODO Auto-generated method stub
		
	}
}
