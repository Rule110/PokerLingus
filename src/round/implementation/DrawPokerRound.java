package round.implementation;

import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import player.framework.Player;
import bank.framework.Bank;
import dealer.framework.Dealer;
import hand.framework.Hand;
import hand.framework.HandFactory;
import hand.implementation.DrawPokerHand;
import hand.implementation.OnePair;
import hand.implementation.PlayingCard;

public class DrawPokerRound extends RoundTemplate {
	
	public static String pokerType = "DrawPoker";
	private Vector<String> openingPlayers;
	private Vector<String> roundOrder;
	
    public DrawPokerRound(Map<String, Player> players, Dealer dealer, Bank bank){
        super(players, dealer, bank);
    }
    
    @Override
    public void beginRound(){
        //Sends start sequence to UI
    	//Players Built in Game.
    	dealHands();
    	//discard phase;
    	openingPlayers = getOpeningPlayers();
    	// Loop through players, tell UI if player can open or not
    	//bettingPhase();
    	
    }

    @Override
    protected void dealHands(){
        //Loop through map players, deal out cards until Hand limit is met
    	for (String p: players.keySet()){
    		//Hand temp = dealer.dealHand();
    		Hand currentHand = HandFactory.getHand(pokerType);

    		for (int i = 0; i < DrawPokerHand.HAND_LIMIT; i++){
    			currentHand.setCards(dealer.dealNext(), i);
    		}  
    		players.get(p).setHand(currentHand);
    	}
    }


    @Override
    protected void beginDiscardPhase(){
    	for (String p: players.keySet()){
    		getDiscardedCards(p);
    	}
    }

    @Override
    protected void getDiscardedCards(String playerID){
        //Darragh will do
    }
    
    @Override
    protected Vector<String> getOpeningPlayers(){
        //return ordered vector of players who CAN open. ie have better than high hand.
    	Vector<String> open = new Vector<String>(players.size());
    	for (String p: players.keySet()){
    		if (players.get(p).getHand().getGameValue() > OnePair.ONE_PAIR_DEFAULT){
    			open.addElement(p);
    		}
    	}
        return open;
    }

    @Override
    protected void beginBettingPhase(){
       String firstPlayer = openingPlayers.firstElement();
       boolean firstFound = false;
       for (String p: players.keySet()){
   			if (p.equals(firstPlayer)){
   				firstFound = true;
   				roundOrder.add(0, p);
   				continue;
   			}
   			if (firstFound){
   				roundOrder.add(p);
   			}
   		}
    }

    @Override
    protected boolean isFolding(String playerID){
        
        return false;
    }

    @Override
    protected int getBet(String playerID, int callValue){
        
        return 0;
    }

    @Override
    public String getWinner(){
        
        return super.winner;
    }

    @Override
    protected void addWinnings(String winner, int potSplit){
        
    }
}
