package network.implementation;

import java.io.IOException;
import java.io.OutputStream;

import twitter4j.*;
import twitter4j.auth.*;

public class TwitterPrintStream extends OutputStream{
    private final static String CONSUMER_KEY = "inTnZhpHxBFnQ7SKUjNvfL1mv";
    private final static String CONSUMER_KEY_SECRET =
      "50YtBK9DXHjsCC2vYbGv6vLhFAUAsaeGVqyuM69LDsklR42edm";
    
	private String userHandle;
	private Twitter twitter;
	
	public TwitterPrintStream(String userHandle){
		this.userHandle = userHandle;
		TwitterFactory factory = new TwitterFactory();
		twitter = factory.getInstance();
		twitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_KEY_SECRET);
		AccessToken accessToken = new AccessToken(getSavedAccessToken(), getSavedAccessTokenSecret());
		twitter.setOAuthAccessToken(accessToken);
	}
	
    private String getSavedAccessTokenSecret() {
        // consider this is method to get your previously saved Access Token
        // Secret
        return "HIYDWwSWuoraBXYW9YjrERCFe06zp9bX7maDm8KwCUosO";
    }

    private String getSavedAccessToken() {
        // consider this is method to get your previously saved Access Token
        return "850829181332189184-ggJdPa7miGGsnJeDexGIjGs6qcmOprt";
    }
    
	@Override
	public void write(int arg0) throws IOException {
		try {
			twitter.sendDirectMessage(userHandle, "hello " + userHandle);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}

}
