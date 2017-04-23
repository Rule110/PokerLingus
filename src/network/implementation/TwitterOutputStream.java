package network.implementation;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import twitter4j.*;
import twitter4j.auth.*;

public class TwitterOutputStream extends OutputStream{
    private static final String CONSUMER_KEY = "inTnZhpHxBFnQ7SKUjNvfL1mv";
    private static final String CONSUMER_KEY_SECRET =
      "50YtBK9DXHjsCC2vYbGv6vLhFAUAsaeGVqyuM69LDsklR42edm";
    
    private static final String ACCESS_TOKEN = "850829181332189184-ggJdPa7miGGsnJeDexGIjGs6qcmOprt";
    private static final String ACCESS_TOKEN_SECRET = "HIYDWwSWuoraBXYW9YjrERCFe06zp9bX7maDm8KwCUosO";
    
	private String userHandle;
	private Twitter twitter;
	
	public TwitterOutputStream(String userHandle){
		this.userHandle = userHandle;
		TwitterFactory factory = new TwitterFactory();
		twitter = factory.getInstance();
		twitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_KEY_SECRET);
		AccessToken accessToken = new AccessToken(ACCESS_TOKEN, ACCESS_TOKEN_SECRET);
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
			twitter.sendDirectMessage(userHandle, writeString);
		} catch (TwitterException e) {
			//System.out.println("write fail");
			e.printStackTrace();
		}
	}
	
	public static void main(String[] argds){
		PrintStream ps= new PrintStream(new TwitterOutputStream("DkfFay"));
		ps.print("waddup, fam");
		ps.close();
	}

}
