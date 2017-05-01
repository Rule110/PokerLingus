package network.implementation;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.sql.Timestamp;

import gfxupdate.framework.GfxUpdate;
import twitter4j.*;
import twitter4j.auth.*;

public class TwitterOutputStream extends OutputStream{
    
	private String userHandle;
	private Twitter twitter;
	
	public TwitterOutputStream(String userHandle){
		this.userHandle = userHandle;
		TwitterFactory factory = new TwitterFactory();
		twitter = factory.getInstance();
		twitter.setOAuthConsumer(TwitterSymbols.CONSUMER_KEY, TwitterSymbols.CONSUMER_KEY_SECRET);
		AccessToken accessToken = new AccessToken(TwitterSymbols.ACCESS_TOKEN, TwitterSymbols.ACCESS_TOKEN_SECRET);
		twitter.setOAuthAccessToken(accessToken);
	}
    
	@Override
	public void write(int arg0) throws IOException {
		byte[] byteRep = {(byte)arg0};
		write(byteRep, 0, byteRep.length);
	}
	
	@Override
	public void write(byte[] byteRep, int offset, int length){
		String writeString = new String(byteRep, offset, length);
		//System.out.println("Sent String: " + writeString);
		try {
			Thread.sleep(500);
			twitter.sendDirectMessage(userHandle, "[" + new Timestamp(System.currentTimeMillis()) + "]\n" + writeString);
		} catch (TwitterException e) {
			//System.out.println("write fail");
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] argds){
		PrintStream ps= new PrintStream(new TwitterOutputStream("DkfFay"));
		ps.print("waddup, fam");
		ps.close();
	}

	public void pushImage(GfxUpdate update) {
		// Currently twitter 4j doesn't allow you to post images
		// via direct messages however this may be added to the API
		// soon as it is the highest requested feature.
	}

}
