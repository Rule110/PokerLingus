package pokerfaice;

import java.util.LinkedHashMap;
import java.util.Map;

import game.framework.Game;
import game.framework.GameFactory;
import network.framework.Network;
import network.framework.NetworkFactory;
import network.implementation.TwitterStreamListener;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.auth.AccessToken;

/**
 * Main class for the PokerFAIce Twitter Bot
 *
 */
public class PokerFAIce {
    public static final String networktype = "Twitter";
    public static final String gameType = "DrawPoker";
    
    private static final String CONSUMER_KEY = "inTnZhpHxBFnQ7SKUjNvfL1mv";
    private static final String CONSUMER_KEY_SECRET =
      "50YtBK9DXHjsCC2vYbGv6vLhFAUAsaeGVqyuM69LDsklR42edm";
    
    private static final String ACCESS_TOKEN = "850829181332189184-ggJdPa7miGGsnJeDexGIjGs6qcmOprt";
    private static final String ACCESS_TOKEN_SECRET = "HIYDWwSWuoraBXYW9YjrERCFe06zp9bX7maDm8KwCUosO";
    
    private static Twitter twitter;
    volatile boolean hasCreationQueued = false;
    /**
     * Main method for the PokerFAIce Twitter Bot
     * @param args
     */
    private Map<String, Game> games;
    //private twitterListener twitterListener;
    
    public PokerFAIce(){
    	games = new LinkedHashMap<String, Game>();
    }
    public static void main(String[] args){
    	PokerFAIce pf = new PokerFAIce();
        TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
        twitter = new TwitterFactory().getInstance();
        
        twitterStream.setOAuthConsumer(CONSUMER_KEY, CONSUMER_KEY_SECRET);
        twitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_KEY_SECRET);
		AccessToken accessToken = new AccessToken(ACCESS_TOKEN, ACCESS_TOKEN_SECRET);
		twitterStream.setOAuthAccessToken(accessToken);
		twitter.setOAuthAccessToken(accessToken);
		
        TwitterStreamListener listener = new TwitterStreamListener(pf);
        twitterStream.addListener(listener);
        twitterStream.user();
        
        while(true){
        	pf.hasCreationQueued = !(listener.getCreationQueue().isEmpty());
        	if(pf.hasCreationQueued){
        		String newUserHandle = listener.popCreationQueue();
        		Network newNetwork = NetworkFactory.getNetwork(networktype, newUserHandle);
        		System.out.println("Network Created");
        		Game newGame = GameFactory.getGame(gameType, newUserHandle, newNetwork);
        		pf.games.put(newUserHandle, newGame);
        		System.out.println("Game Created");
        		pf.getGame(newUserHandle).start();
        		System.out.print("Game running");
        	}
        }
        
    }
    
    public Game getGame(String userHandle){
    	return games.get(userHandle);
    }
    
    public void deleteMessage(long messageId){
    	try {
			twitter.destroyDirectMessage(messageId);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}