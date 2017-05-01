package ui.implementation;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;
import bank.framework.Bank;
import game.framework.Game;
import gfxupdate.implementation.CardGfxUpdate;
import hand.framework.Hand;
import hand.implementation.PlayingCard;
import network.framework.Network;
import network.implementation.LocalNetwork;
import round.framework.Round;
import textupdate.implementation.GameStateTextUpdate;

/**
 * This class models a text based user interface for the game
 * @author Darragh
 */
public class TextualUI extends UITemplate {
    
	//private Network network = new LocalNetwork("testPlayer");
	private GameStateTextUpdate message;
	
	/**
	 * Constructor takes in user identification
	 * and a reference to the game thread the player is a part of.
	 * @param game
	 * @param ID
	 */
    public TextualUI(Game game, String ID){
        super(game, ID);
        message = new GameStateTextUpdate();
    }
    
    /**
     * This method sets the booleans indicating the players when it is their turn.
     */
    public void decideStrategy(Hand hand, Round round){
    	boolean strategy = false;
    	
    	while(!strategy){
	        if(!setFolding()){
		        if(!setRaising()){
		        	if(setCalling()){
		        		strategy = true;
		        	}
		        } else{
		        	setRaise(Math.max(round.getAvailableFunds(ID) - round.getCallValue(), 0));
		        	strategy = true;
		        }
	        } else
	        	strategy = true;
    	}
    }
    
    /**
     * This method asks the user if they want to fold and sets a boolean based on their response
     * @return isFolding
     */
    public boolean setFolding(){
    	isFolding = acceptanceLoop("Would you like to fold (y/n)?: ");
    	return isFolding;
    }
    
    /**
     * This method asks the user if they want to call and sets a boolean based on their response
     * @return isCalling
     */
    public boolean setCalling(){
    	isCalling = acceptanceLoop("Would you like to call (y/n)?: ");
    	return isCalling;
    }
    
    /**
     * This method asks the user if they want to raise and sets a boolean based on their response
     * @return isRaising
     */
    public boolean setRaising(){
    	isRaising = acceptanceLoop("Would you like to raise (y/n)?: ");
    	return isRaising;
    }
    
    /**
     * This method takes in the players chips less the call value
     * and asks how much of they're loose chips they want to raise with.
     * @param playerChips
     */
    public void setRaise(int playerChips){
    	Boolean validAmount = false;
    	
    	while(validAmount != true){
    		message.setText("Please enter amount to raise by: (max=" + playerChips + ")");
        	game.pushTextUpdate(message);
    		try {
    			raiseAmount = Integer.parseInt(game.getMessageUpdate());
            } catch (NumberFormatException e) {
            	message.setText("Please Enter A valid Integer");
            	game.pushTextUpdate(message);
                continue;
            }        	
        	if (raiseAmount > playerChips){
        		message.setText("You cannot bet more chips than you have!");
        		game.pushTextUpdate(message);
        	}else if (raiseAmount <= 0){
        		message.setText("Please enter a valid amount to raise by!");
        		game.pushTextUpdate(message);
        	}else
        		validAmount = true;
    	}
    }

    /**
     * This method prompts the user to provide indices corresponding to cards
     * they want to swap out and returns a vector corresponding to said indices.
     * @return discardIndices
     */
	@Override
	public Vector<Integer> decideDiscarding() {
		message.setText("Which card(s) would you like to discard (e.g. 1,3)?(Type non number characters to skip discarding)");
		game.pushTextUpdate(message);

		String discardCards = game.getMessageUpdate();
		Scanner discardScan = new Scanner(discardCards);
		boolean validInput = false;
		Vector<Integer> discardIndices = new Vector<Integer>();
		
	
			while(discardScan.hasNextInt() && discardIndices.size() < 3){
				int index = discardScan.nextInt();
				if(index < 0 || index > 4){
					message.setText("Invalid input for card indices (should be 0-4), Please Try again");
					game.pushTextUpdate(message);
					discardIndices.clear();
					continue;
				}
				discardIndices.addElement(index);
				validInput = true;
			}
			discardScan.close();
		return discardIndices;
	}

	/**
	 * Method displays the user's hand of cards on their output device.
	 */
	@Override
	public void checkHand(int chips, Hand hand) {
		checkHand(hand);
		message.setText(ID + "'s Chips: " + Integer.toString(chips) + "\n");
		game.pushTextUpdate(message);
	}
	@Override
	public void checkHand(Hand hand) {
		String handStr = ID + "'s hand: ";
		Iterator<PlayingCard> it = hand.iterator();
		while(it.hasNext()){
			handStr += "[" + it.next() + "]";
		}
		// Twitter 4j has yet to provide support for direct messages with pictures
		//CardGfxUpdate cardGraphic= new CardGfxUpdate();
		//cardGraphic.generateImage(chips, hand);
		//game.pushGfxUpdate(cardGraphic);
		
		message.setText(handStr + "\n");
		game.pushTextUpdate(message);
	}
	
	private boolean acceptanceLoop(String prompt){
		boolean isAccepting = false;
		boolean isValid     = false;
		String accept = "";
    	
    	while (!isValid){
    		message.setText(prompt);
        	game.pushTextUpdate(message);
        	accept = game.getMessageUpdate();
        	if(accept.toLowerCase().equals("y")){
        		isAccepting =  isValid  = true;
        	}else if(accept.toLowerCase().equals("n")){
        		isAccepting =  !(isValid = true);
        	}else{
        		message.setText("Invalid input, please enter (y/n)\n");
            	game.pushTextUpdate(message);
        	}
    	}
    	return isAccepting;
	}
}
