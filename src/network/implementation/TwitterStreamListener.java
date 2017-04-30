package network.implementation;

import java.util.LinkedList;
import java.util.Queue;

import game.framework.Game;
import pokerfaice.PokerFAIce;
import twitter4j.*;

public class TwitterStreamListener implements UserStreamListener{

	private Queue<String> creationQueue = new LinkedList<String>();
	private static String controlHandle = "PokerLingus";
	private PokerFAIce pfRef;
	
	public TwitterStreamListener(PokerFAIce pf){
		pfRef = pf;
	}
	@Override
	public void onStatus(Status arg0) {
		boolean inCreation = creationQueue.contains(arg0.getUser().getScreenName());
		boolean inGame = (pfRef.getGame(arg0.getUser().getScreenName()) != null);
		if(!(inCreation || inGame)){
			creationQueue.add(arg0.getUser().getScreenName());
		}
	}
	
	@Override
	public void onDirectMessage(DirectMessage arg0) {
		String sender = arg0.getSender().getScreenName();
		System.out.println(sender);
		
		if(!sender.equals(controlHandle)){
			//System.out.println(arg0.getText());
			Game relevantGame = pfRef.getGame(sender);
			if(relevantGame != null){
				System.out.println("Captured");
				relevantGame.captureMessageUpdate(arg0.getText());
				relevantGame.notify();
			}else if(!creationQueue.contains(arg0.getSender().getScreenName())){
				creationQueue.add(arg0.getSender().getScreenName());
			}
			System.out.println(this.creationQueue);
		}
		pfRef.deleteMessage(arg0.getId());
	}
	
	public Queue<String> getCreationQueue(){
		return this.creationQueue;
	}
	
	public String popCreationQueue(){
		return creationQueue.remove();
	}
	
	@Override
	public void onDeletionNotice(StatusDeletionNotice arg0) {}

	@Override
	public void onScrubGeo(long arg0, long arg1) {}

	@Override
	public void onStallWarning(StallWarning arg0) {}
	@Override
	public void onTrackLimitationNotice(int arg0) {}

	@Override
	public void onException(Exception arg0) {}

	@Override
	public void onBlock(User arg0, User arg1) {}

	@Override
	public void onDeletionNotice(long arg0, long arg1) {}

	@Override
	public void onFavorite(User arg0, User arg1, Status arg2) {}

	@Override
	public void onFavoritedRetweet(User arg0, User arg1, Status arg2) {}

	@Override
	public void onFollow(User arg0, User arg1) {}

	@Override
	public void onFriendList(long[] arg0) {}

	@Override
	public void onQuotedTweet(User arg0, User arg1, Status arg2) {}

	@Override
	public void onRetweetedRetweet(User arg0, User arg1, Status arg2) {}

	@Override
	public void onUnblock(User arg0, User arg1) {}

	@Override
	public void onUnfavorite(User arg0, User arg1, Status arg2) {}

	@Override
	public void onUnfollow(User arg0, User arg1) {}

	@Override
	public void onUserDeletion(long arg0) {}

	@Override
	public void onUserListCreation(User arg0, UserList arg1) {}

	@Override
	public void onUserListDeletion(User arg0, UserList arg1) {}

	@Override
	public void onUserListMemberAddition(User arg0, User arg1, UserList arg2) {}

	@Override
	public void onUserListMemberDeletion(User arg0, User arg1, UserList arg2) {}

	@Override
	public void onUserListSubscription(User arg0, User arg1, UserList arg2) {}

	@Override
	public void onUserListUnsubscription(User arg0, User arg1, UserList arg2) {}

	@Override
	public void onUserListUpdate(User arg0, UserList arg1) {}

	@Override
	public void onUserProfileUpdate(User arg0) {//action not defined in implementation
	}

	@Override
	public void onUserSuspension(long arg0) {}

}
