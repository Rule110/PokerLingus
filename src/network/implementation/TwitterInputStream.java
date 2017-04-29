package network.implementation;

import java.io.IOException;
import java.io.InputStream;
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
	private volatile StringBuilder currentIn;
	//private int index = 0;
	private Twitter twitter;
	
	public TwitterInputStream(){
		this.userHandle = userHandle;
		
		TwitterFactory factory = new TwitterFactory();
		twitter = factory.getInstance();
		twitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_KEY_SECRET);
		AccessToken accessToken = new AccessToken(ACCESS_TOKEN, ACCESS_TOKEN_SECRET);
		twitter.setOAuthAccessToken(accessToken);
		
		//mutable string builder for storing input from twitter
		currentIn = new StringBuilder();
	}
	
	@Override
	public synchronized int read() throws IOException {
		if(available() == 0){
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//Thread.currentThread().interrupt();
		}
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
		System.out.println("captured:" + currentIn);
	}
	
	public static void main(String[] args) throws InterruptedException{

	}

}
