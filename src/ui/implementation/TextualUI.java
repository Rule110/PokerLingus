package ui.implementation;

import java.io.PrintStream;
import java.util.Scanner;

import game.framework.Game;
import hand.framework.Hand;
import round.framework.Round;

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
    	outStream.println("Welcome to the Automated Poker Machine... \nLet's Play Poker!");
    }
    
    public boolean isFolding(){
    	String fold;
    	Boolean response = null;
    	
    	while(response != true || response != false){
    		outStream.println("Would you like to fold (y/n)?: ");
        	fold = input.nextLine();
        	
        	switch (fold.toLowerCase()){
	    		case "y":
	    			response = true;
	    			break;
	    		case "n":
	    			response = false;
	    			break;
	    		default:
	    			outStream.println("Please enter a valid character!");
	    			break;    	
        	}
    	}
    	return response;
    }
    
    public boolean isCalling(){
    	String call;
    	
    	while(isCalling != true || isCalling != false){
    		outStream.println("Would you like to call (y/n)?: ");
        	call = input.nextLine();
        	
        	switch (call.toLowerCase()){
	    		case "y":
	    			isCalling = true;
	    			break;
	    		case "n":
	    			isCalling = false;
	    			break;
	    		default:
	    			outStream.println("Please enter a valid character!");
	    			break;    	
        	}
    	}
    	return isCalling;
    }
    
    public boolean isRaising(){
    	String raise;
    	
    	while(isRaising != true || isRaising != false){
    		outStream.println("Would you like to raise (y/n)?: ");
        	raise = input.nextLine();
        	
        	switch (raise.toLowerCase()){
	    		case "y":
	    			isRaising = true;
	    			break;
	    		case "n":
	    			isRaising = false;
	    			break;
	    		default:
	    			outStream.println("Please enter a valid character!");
	    			break;    	
        	}
    	}
    	return isRaising;
    }
    
    public int getRaise(){
        int playerChips = 0;
    	int raiseAmount = 1;
    	Boolean validAmount = false;
    	
    	while(validAmount != true){
    		outStream.println("Please enter amount to raise by: ");
    		try {
    			raiseAmount = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please Enter A valid Integer");
                continue;
            }        	
        	if (raiseAmount > playerChips)
        		outStream.println("You cannot bet more chips than you have!");
        	else if (raiseAmount <= 0)
        		outStream.println("Please enter an amount to raise by!");
        	else
        		validAmount = true;
    	}
    	return raiseAmount;
    }
}
