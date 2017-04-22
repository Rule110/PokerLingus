package ui.implementation;

import java.io.PrintStream;
import java.util.Scanner;

import hand.framework.Hand;
import network.framework.Network;
import round.framework.Round;

public class TextualUI extends UITemplate {
    public TextualUI(Network network){
        super(network);
    }
    
    @Override
    public void decideStrategy(Hand hand, Round round){
        
    }
    
    public boolean isFolding(){
    	PrintStream outStream = System.out;
    	Scanner input = new Scanner(System.in);
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
    	PrintStream outStream = System.out;
    	Scanner input = new Scanner(System.in);
    	String call;
    	Boolean response = null;
    	
    	while(response != true || response != false){
    		outStream.println("Would you like to call (y/n)?: ");
        	call = input.nextLine();
        	
        	switch (call.toLowerCase()){
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
    
    public boolean isRaising(){
    	PrintStream outStream = System.out;
    	Scanner input = new Scanner(System.in);
    	String raise;
    	Boolean response = null;
    	
    	while(response != true || response != false){
    		outStream.println("Would you like to raise (y/n)?: ");
        	raise = input.nextLine();
        	
        	switch (raise.toLowerCase()){
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
    
    public int getRaise(int playerChips){
    	PrintStream outStream = System.out;
    	Scanner input = new Scanner(System.in);
    	int raiseAmount = 1;
    	Boolean validAmount = false;
    	
    	while(validAmount != true){
    		outStream.println("Please enter amount to raise by: ");
        	raiseAmount = Integer.parseInt(input.nextLine());
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
