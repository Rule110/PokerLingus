package network.implementation;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.function.Predicate;

import twitter4j.DirectMessage;
import twitter4j.ResponseList;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class TwitterInputStream extends InputStream {
    private static final String CONSUMER_KEY = "inTnZhpHxBFnQ7SKUjNvfL1mv";
    private static final String CONSUMER_KEY_SECRET =
      "50YtBK9DXHjsCC2vYbGv6vLhFAUAsaeGVqyuM69LDsklR42edm";
    
    private static final String ACCESS_TOKEN = "850829181332189184-ggJdPa7miGGsnJeDexGIjGs6qcmOprt";
    private static final String ACCESS_TOKEN_SECRET = "HIYDWwSWuoraBXYW9YjrERCFe06zp9bX7maDm8KwCUosO";
    
	private String userHandle;
	private StringBuilder currentIn;
	private Twitter twitter;
	
	public TwitterInputStream(String userHandle){
		this.userHandle = userHandle;
		
		TwitterFactory factory = new TwitterFactory();
		twitter = factory.getInstance();
		twitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_KEY_SECRET);
		AccessToken accessToken = new AccessToken(ACCESS_TOKEN, ACCESS_TOKEN_SECRET);
		twitter.setOAuthAccessToken(accessToken);
		
		currentIn = new StringBuilder();
	}
	
	@Override
	public int read() throws IOException {
		if(currentIn.length() > 0){
			int readByte = (int)((byte)currentIn.charAt(0));
			currentIn = new StringBuilder(currentIn.substring(1));
			return readByte;
		}
		return -1;
	}
	
	@Override
	public int available(){
		return currentIn.length();
		
	}
	public void captureMessage(String messageString){
		ResponseList<DirectMessage> dm;
		try {
			dm = twitter.getDirectMessages();
			Predicate <DirectMessage> dmNotRelevant = se-> (!(se.getSender().getScreenName().equals(userHandle)));
			dm.removeIf(dmNotRelevant);
			
			for(DirectMessage d : dm){
				currentIn.append((d.getText()) + "\n");
			}
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] argds){
		TwitterInputStream tw  = new TwitterInputStream("DkfFay");
		tw.captureMessage("lol");
		Scanner ps= new Scanner(tw);
		System.out.println(ps.nextLine());
		ps.close();
	}

}
