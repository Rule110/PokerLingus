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
        outputPrinter.print(textupdate.getText());
    }
    
    public synchronized void sendGfxUpdate(GfxUpdate gfxupdate){
        
    }
    
	@Override
	public synchronized void captureMessageUpdate(String newMessage) {
		inputStream.captureMessage(newMessage);
	}
	
    public String getUserHandle(){
    	return this.userHandle;
    }

	@Override
	public void pushMessageUpdate(String update) {
		 outputPrinter.print(update);
	}
}
