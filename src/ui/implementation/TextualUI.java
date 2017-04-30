package ui.implementation;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;
import bank.framework.Bank;
import game.framework.Game;
import hand.framework.Hand;
import hand.implementation.PlayingCard;
import network.framework.Network;
import network.implementation.LocalNetwork;
import round.framework.Round;
import textupdate.implementation.GameStateTextUpdate;

public class TextualUI extends UITemplate {
    
	//private Network network = new LocalNetwork("testPlayer");
	private GameStateTextUpdate message;
	
    public TextualUI(Game game){
        super(game);
        message = new GameStateTextUpdate();
    }
    
    public void textualDiscard(){
    	message.setText("Which card(s) would you like to discard (e.g., 1,3): ");
    	game.pushTextUpdate(message);
    }
    
    public void decideStrategy(Hand hand, Round round){
    	boolean strategy = false;
    	
    	while(!strategy){
	        if(!setFolding()){
		        if(!setRaising()){
		        	if(setCalling()){
		        		strategy = true;
		        	}
		        } else{
		        	setRaise(10);
		        	strategy = true;
		        }
	        } else
	        	strategy = true;
    	}
    }
    
    public boolean setFolding(){
    	String fold;

		message.setText("Would you like to fold (y/n)?: ");
    	game.pushTextUpdate(message);
    	fold = game.getMessageUpdate();
    	
    	switch (fold.toLowerCase()){
    		case "y":
    			isFolding = true;
    			break;
    		case "n":
    			isFolding = false;
    			break;
    		default:
    			message.setText("Please enter a valid character!");
            	game.pushTextUpdate(message);
            	//message.setText("Would you like to fold (y/n)?: ");
            	//network.sendTextUpdate(message);
            	//fold = network.getMessageUpdate();
            	//System.out.println(fold);
            	setFolding();
           }
    	return isFolding;
    }
    
    public boolean setCalling(){
    	String call;

		message.setText("Would you like to call (y/n)?: ");
    	game.pushTextUpdate(message);
    	call = game.getMessageUpdate();
    	
    	switch (call.toLowerCase()){
    		case "y":
    			isCalling = true;
    			break;
    		case "n":
    			isCalling = false;
    			break;
    		default:
    			message.setText("Please enter a valid character!");
            	game.pushTextUpdate(message);
            	//message.setText("Would you like to call (y/n)?: ");
            	//network.sendTextUpdate(message);
            	//call = network.getMessageUpdate();   
            	setCalling();
    	}
    	return isCalling;
    }
    
    public boolean setRaising(){
    	String raise;

		message.setText("Would you like to raise (y/n)?: ");
    	game.pushTextUpdate(message);
    	raise = game.getMessageUpdate();
    	
    	switch (raise.toLowerCase()){
    		case "y":
    			isRaising = true;
    			break;
    		case "n":
    			isRaising = false;
    			break;
    		default:
    			message.setText("Please enter a valid character!");
            	game.pushTextUpdate(message);
            	//message.setText("Would you like to raise (y/n)?: ");
            	//network.sendTextUpdate(message);
            	//raise = network.getMessageUpdate(); 
            	setRaising();
    	}
    	return isRaising;
    }
    
    public void setRaise(int playerChips){
    	Boolean validAmount = false;
    	
    	while(validAmount != true){
    		message.setText("Please enter amount to raise by: ");
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
        		message.setText("Please enter an amount to raise by!");
        		game.pushTextUpdate(message);
        	}else
        		validAmount = true;
    	}
    }

	@Override
	public Vector<Integer> decideDiscarding() {
		message.setText("Which card(s) would you like to discard (e.g. 1,3)?");
		game.pushTextUpdate(message);
		
		String discardCards = game.getMessageUpdate();
		Vector<Integer> discardIndices = new Vector<Integer>();
		Scanner discardScan = new Scanner(discardCards);
		boolean inputValid = true;
		
		while(discardScan.hasNext()){
			int index = discardScan.nextInt();
			if(index < 0 || index > 4){
				message.setText("Invalid input for card indices (should be 0-4), Please Try again");
				game.pushTextUpdate(message);
				discardIndices.clear();
				continue;
			}
			discardIndices.addElement(index);
		}
		return discardIndices;
	}

	@Override
	public void checkHand(Hand hand) {
		String handStr = "";
		Iterator<PlayingCard> it = hand.iterator();
		while(it.hasNext()){
			handStr += "[" + it.next() + "]";
		}
		message.setText(handStr);
		game.pushTextUpdate(message);
	}
}
