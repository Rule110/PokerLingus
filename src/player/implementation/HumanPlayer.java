package player.implementation;

import java.io.PrintStream;
import java.util.Scanner;

import network.framework.Network;
import round.framework.Round;
import ui.framework.UI;
import ui.framework.UIFactory;

public class HumanPlayer extends PlayerTemplate {
    private UI ui;
    
    public HumanPlayer(Network network){
        this.ui = UIFactory.getUI("Textual", network);
    }
    
    @Override
    public void decideStrategy(Round round){
        this.ui.decideStrategy(super.hand, round);
    }
    
    public boolean isFolding(){
    	PrintStream outStream = System.out;
    	
    	outStream.println("Would you like to fold (y/n)?: ");
    	
    	Scanner input = new Scanner(System.in);
    	String fold = input.nextLine();
    	
    	switch (fold.toLowerCase()){
    		case "y":
    			return true;
    		case "n":
    			return false;
    		default:
    			outStream.println("Please enter a valid character!");
    			isFolding();    	
    	}
    	return false;
    }
    
    public boolean isCalling(){
    	PrintStream outStream = System.out;
    	
    	outStream.println("Would you like to call (y/n)?: ");
    	
    	Scanner input = new Scanner(System.in);
    	String call = input.nextLine();
    	
    	switch (call.toLowerCase()){
    		case "y":
    			return true;
    		case "n":
    			return false;
    		default:
    			outStream.println("Please enter a valid character!");
    			isCalling();    	
    	}
    	return false;
    }
    
    public boolean isRaising(){
    	PrintStream outStream = System.out;
    	
    	outStream.println("Would you like to raise (y/n)?: ");
    	
    	Scanner input = new Scanner(System.in);
    	String raise = input.nextLine();
    	
    	switch (raise.toLowerCase()){
    		case "y":
    			return true;
    		case "n":
    			return false;
    		default:
    			outStream.println("Please enter a valid character!");
    			isRaising();    	
    	}
    	return false;
    }
    
    public int getRaise(int currMax){
    	PrintStream outStream = System.out;
    	
    	outStream.println("How much would you like to raise by (amount)?: ");
    	
    	Scanner input = new Scanner(System.in);
    	String raise = input.nextLine();
    	int raiseAmount = Integer.parseInt(raise);
    	
    	if (raiseAmount > /* chips */) {
    		outStream.println("You cannont bet more chips than you have!");
    		getRaise();
    	} else if (raiseAmount <= currMax) {
    		outStream.println("Please enter an amount larger than the last bet!");
    		getRaise();
    	} else
    		return raiseAmount;
    }
}
