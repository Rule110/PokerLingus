package ui.implementation;

import java.io.PrintStream;
import java.util.Scanner;

import game.framework.Game;
import hand.framework.Hand;
import network.framework.Network;
import network.implementation.LocalNetwork;
import round.framework.Round;
import textupdate.framework.TextUpdate;
import textupdate.implementation.GameStateTextUpdate;

public class TextualUI extends UITemplate {
    
	Network network = new LocalNetwork("testPlayer");
	PrintStream outStream = System.out;
	Scanner input = new Scanner(System.in);
	GameStateTextUpdate message;
	
    public TextualUI(Game game){
        super(game);
        message = new GameStateTextUpdate();
    }
    
    public void decideStrategy(Hand hand, Round round){
        if(isFolding()){
        	
        } else if(isRaising()){
        	
        } else if(isCalling()){
        	
        }
    }
    
    public void intro(){
    	message.setText("Welcome to the Automated Poker Machine... \nLet's Play Poker!");
    	network.sendTextUpdate(message);
    }
    
    public boolean isFolding(){
    	String fold;
    	
    	while(isFolding != true || isFolding != false){
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
	    			break;    	
        	}
    	}
    	return isFolding;
    }
    
    public boolean isCalling(){
    	String call;
    	
    	while(isCalling != true || isCalling != false){
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
	    			break;    	
        	}
    	}
    	return isCalling;
    }
    
    public boolean isRaising(){
    	String raise;
    	
    	while(isRaising != true || isRaising != false){
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
	    			break;    	
        	}
    	}
    	return isRaising;
    }
    
    public int getRaise(){
        int playerChips = 0;
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
    	return raiseAmount;
    }
}
