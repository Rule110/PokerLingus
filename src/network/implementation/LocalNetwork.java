package network.implementation;

import java.io.PrintStream;
import java.util.Scanner;

import gfxupdate.framework.GfxUpdate;
import textupdate.framework.TextUpdate;
import network.framework.Network;

public class LocalNetwork implements Network {

	private String userHandle;
	private Scanner inputScanner;
	private PrintStream outputPrinter;
	
	public LocalNetwork(String userHandle){
		this.userHandle = userHandle;
		inputScanner = new Scanner(System.in);
		outputPrinter = System.out;
		
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
	}
	
    public String getUserHandle(){
    	return this.userHandle;
    }

	@Override
	public void pushMessageUpdate(String update) {
		outputPrinter.println(update);
	}

	@Override
	public void pushGFxUpdate(GfxUpdate update) {
	}
}

