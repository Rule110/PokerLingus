package network.implementation;

import java.io.IOException;
import java.io.InputStream;

public class TwitterInputStream extends InputStream {
    
	private volatile StringBuilder currentIn;
	
	public TwitterInputStream(){	
		//mutable string builder for storing input from twitter
		currentIn = new StringBuilder();
	}
	
	@Override
	public synchronized int read() throws IOException {
		if(currentIn.length() > 0){
			int readByte = (int)(currentIn.charAt(0));
			currentIn = currentIn.deleteCharAt(0);
			return readByte;
		}
		return -1;
	}
	
	@Override
	public int available(){
		return currentIn.length();
		
	}
	public synchronized void captureMessage(String messageString){
		currentIn.append(messageString);
	}
	
	public static void main(String[] args) throws InterruptedException{

	}

}
