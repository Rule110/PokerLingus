package ui.implementation;

import java.io.PrintStream;
import java.util.Scanner;

import game.framework.Game;
import hand.framework.Hand;
import round.framework.Round;
import textupdate.framework.TextUpdate;

public class TextualUI extends UITemplate {
    
	PrintStream outStream = System.out;
	Scanner input = new Scanner(System.in);
	
    public TextualUI(Game game){
        super(game);
    }
    
    public void decideStrategy(Hand hand, Round round){
        if(isFolding()){
        	
        } else if(isRaising()){
        	
        } else if(isCalling()){
        	
        }
    }
    
    public void intro(){
    	super.game.pushMessageUpdate("Welcome to the Automated Poker Machine... \nLet's Play Poker!");
    }
    
    public boolean isFolding(){
    	String fold;
    	
    	while(isFolding != true || isFolding != false){
    		super.game.pushMessageUpdate("Would you like to fold (y/n)?: ");
        	fold = super.game.getMessageUpdate();
        	
        	switch (fold.toLowerCase()){
	    		case "y":
	    			isFolding = true;
	    			break;
	    		case "n":
	    			isFolding = false;
	    			break;
	    		default:
	    			super.game.pushMessageUpdate("Please enter a valid character!");
	    			break;    	
        	}
    	}
    	return isFolding;
    }
    
    public boolean isCalling(){
    	String call;
    	
    	while(isCalling != true || isCalling != false){
    		super.game.pushMessageUpdate("Would you like to call (y/n)?: ");
        	call = super.game.getMessageUpdate();
        	
        	switch (call.toLowerCase()){
	    		case "y":
	    			isCalling = true;
	    			break;
	    		case "n":
	    			isCalling = false;
	    			break;
	    		default:
	    			super.game.pushMessageUpdate("Please enter a valid character!");
	    			break;    	
        	}
    	}
    	return isCalling;
    }
    
    public boolean isRaising(){
    	String raise;
    	
    	while(isRaising != true || isRaising != false){
    		super.game.pushMessageUpdate("Would you like to raise (y/n)?: ");
        	raise = super.game.getMessageUpdate();
        	
        	switch (raise.toLowerCase()){
	    		case "y":
	    			isRaising = true;
	    			break;
	    		case "n":
	    			isRaising = false;
	    			break;
	    		default:
	    			super.game.pushMessageUpdate("Please enter a valid character!");
	    			break;    	
        	}
    	}
    	return isRaising;
    }
    
    public int getRaise(){
        int playerChips = 0;
    	Boolean validAmount = false;
    	
    	while(validAmount != true){
    		super.game.pushMessageUpdate("Please enter amount to raise by: ");
    		try {
    			raiseAmount = Integer.parseInt(super.game.getMessageUpdate());
            } catch (NumberFormatException e) {
            	super.game.pushMessageUpdate("Please Enter A valid Integer");
                continue;
            }        	
        	if (raiseAmount > playerChips)
        		super.game.pushMessageUpdate("You cannot bet more chips than you have!");
        	else if (raiseAmount <= 0)
        		super.game.pushMessageUpdate("Please enter an amount to raise by!");
        	else
        		validAmount = true;
    	}
    	return raiseAmount;
    }
}
