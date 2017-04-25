package network.implementation;

import java.util.LinkedList;
import java.util.Queue;

import game.framework.Game;
import pokerfaice.PokerFAIce;
import twitter4j.*;

public class TwitterStreamListener implements UserStreamListener{

	private Queue<String> creationQueue = new LinkedList<String>();
	private PokerFAIce pfRef;
	
	public TwitterStreamListener(PokerFAIce pf){
		pfRef = pf;
	}
	@Override
	public void onDeletionNotice(StatusDeletionNotice arg0) {
		
	}

	@Override
	public void onScrubGeo(long arg0, long arg1) {
		
	}

	@Override
	public void onStallWarning(StallWarning arg0) {
		
	}

	@Override
	public void onStatus(Status arg0) {
		if(!creationQueue.contains(arg0.getUser().getScreenName())){
			creationQueue.add(arg0.getUser().getScreenName());
		}
	}

	public Queue<String> getCreationQueue(){
		return this.creationQueue;
	}
	
	public String popCreationQueue(){
		return creationQueue.remove();
	}
	@Override
	public void onTrackLimitationNotice(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onException(Exception arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBlock(User arg0, User arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDeletionNotice(long arg0, long arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDirectMessage(DirectMessage arg0) {
		String sender = arg0.getSender().getScreenName();
		System.out.println(sender);
		if(!sender.equals("PokerLingus")){
			Game relevantGame = pfRef.getGame(sender);
			if(relevantGame != null){
				relevantGame.captureMessageUpdate(arg0.getText());
				relevantGame.notify();
			}else if(!creationQueue.contains(arg0.getSender().getScreenName())){
				creationQueue.add(arg0.getSenderScreenName());
			}
			System.out.println(this.creationQueue);
		}
	}

	@Override
	public void onFavorite(User arg0, User arg1, Status arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFavoritedRetweet(User arg0, User arg1, Status arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFollow(User arg0, User arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFriendList(long[] arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onQuotedTweet(User arg0, User arg1, Status arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRetweetedRetweet(User arg0, User arg1, Status arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUnblock(User arg0, User arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUnfavorite(User arg0, User arg1, Status arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUnfollow(User arg0, User arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUserDeletion(long arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUserListCreation(User arg0, UserList arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUserListDeletion(User arg0, UserList arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUserListMemberAddition(User arg0, User arg1, UserList arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUserListMemberDeletion(User arg0, User arg1, UserList arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUserListSubscription(User arg0, User arg1, UserList arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUserListUnsubscription(User arg0, User arg1, UserList arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUserListUpdate(User arg0, UserList arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUserProfileUpdate(User arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUserSuspension(long arg0) {
		// TODO Auto-generated method stub
		
	}

}
