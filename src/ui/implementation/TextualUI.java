package ui.implementation;

import bank.framework.Bank;
import game.framework.Game;
import hand.framework.Hand;
import network.framework.Network;
import network.implementation.LocalNetwork;
import round.framework.Round;
import textupdate.implementation.GameStateTextUpdate;

public class TextualUI extends UITemplate {
    
	private Network network = new LocalNetwork("testPlayer");
	private GameStateTextUpdate message;
	
    public TextualUI(Game game){
        super(game);
        message = new GameStateTextUpdate();
    }
    
    public void textualDiscard(){
    	message.setText("Which card(s) would you like to discard (e.g., 1,3): ");
    	network.sendTextUpdate(message);
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
    	network.sendTextUpdate(message);
    	fold = network.getMessageUpdate();
    	
    	switch (fold.toLowerCase()){
    		case "y":
    			isFolding = true;
    			break;
    		case "n":
    			isFolding = false;
    			break;
    		default:
    			message.setText("Please enter a valid character!");
            	network.sendTextUpdate(message);
            	//message.setText("Would you like to fold (y/n)?: ");
            	//network.sendTextUpdate(message);
            	//fold = network.getMessageUpdate();
            	//System.out.println(fold);
           }
    	return isFolding;
    }
    
    public boolean setCalling(){
    	String call;

		message.setText("Would you like to call (y/n)?: ");
    	network.sendTextUpdate(message);
    	call = network.getMessageUpdate();
    	
    	switch (call.toLowerCase()){
    		case "y":
    			isCalling = true;
    			break;
    		case "n":
    			isCalling = false;
    			break;
    		default:
    			message.setText("Please enter a valid character!");
            	network.sendTextUpdate(message);
            	//message.setText("Would you like to call (y/n)?: ");
            	//network.sendTextUpdate(message);
            	//call = network.getMessageUpdate();   	
    	}
    	return isCalling;
    }
    
    public boolean setRaising(){
    	String raise;

		message.setText("Would you like to raise (y/n)?: ");
    	network.sendTextUpdate(message);
    	raise = network.getMessageUpdate();
    	
    	switch (raise.toLowerCase()){
    		case "y":
    			isRaising = true;
    			break;
    		case "n":
    			isRaising = false;
    			break;
    		default:
    			message.setText("Please enter a valid character!");
            	network.sendTextUpdate(message);
            	//message.setText("Would you like to raise (y/n)?: ");
            	//network.sendTextUpdate(message);
            	//raise = network.getMessageUpdate();   	
    	}
    	return isRaising;
    }
    
    public void setRaise(int playerChips){
    	Boolean validAmount = false;
    	
    	while(validAmount != true){
    		message.setText("Please enter amount to raise by: ");
        	network.sendTextUpdate(message);
    		try {
    			raiseAmount = Integer.parseInt(network.getMessageUpdate());
            } catch (NumberFormatException e) {
            	message.setText("Please Enter A valid Integer");
            	network.sendTextUpdate(message);
                continue;
            }        	
        	if (raiseAmount > playerChips){
        		message.setText("You cannot bet more chips than you have!");
        		network.sendTextUpdate(message);
        	}else if (raiseAmount <= 0){
        		message.setText("Please enter an amount to raise by!");
        		network.sendTextUpdate(message);
        	}else
        		validAmount = true;
    	}
    }
}
