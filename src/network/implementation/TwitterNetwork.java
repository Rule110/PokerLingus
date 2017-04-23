package network.implementation;

import java.io.PrintStream;
import java.util.Scanner;

import gfxupdate.framework.GfxUpdate;
import textupdate.framework.TextUpdate;
import network.framework.Network;

public class TwitterNetwork implements Network {
	
	private String userHandle;
	private TwitterInputStream inputStream;
	private TwitterOutputStream outputStream;
	private Scanner inputScanner;
	private PrintStream outputPrinter;
	
	public TwitterNetwork(String userHandle){
		this.userHandle = userHandle;
		inputStream = new TwitterInputStream(userHandle);
		outputStream = new TwitterOutputStream(userHandle);
		inputScanner = new Scanner(inputStream);
		outputPrinter = new PrintStream(outputStream);
		
	}
	
	public synchronized String getMessageUpdate(){
		return inputScanner.nextLine();
	}
    public synchronized void sendTextUpdate(TextUpdate textupdate){
        outputPrinter.println(textupdate.getText());
    }
    
    public synchronized void sendGfxUpdate(GfxUpdate gfxupdate){
        
    }
    
    public String getUserHandle(){
    	return this.userHandle;
    }
}
