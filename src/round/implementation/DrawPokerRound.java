package round.implementation;

import java.util.LinkedList;
import java.util.Map;
import java.util.Vector;

import player.framework.Player;
import bank.framework.Bank;
import dealer.framework.Dealer;
import hand.framework.Hand;
import hand.framework.HandFactory;
import hand.implementation.DrawPokerHand;
import hand.implementation.OnePair;

public class DrawPokerRound extends RoundTemplate {
	
	public static String pokerType = "DrawPoker";
	private Vector<String> openingPlayers;
	private LinkedList<String> roundOrder;
	
    public DrawPokerRound(Map<String, Player> players, Dealer dealer, Bank bank){
        super(players, dealer, bank);
    }
    
    @Override
    public void beginRound(){
        //Sends start sequence to UI
    	//Players Built in Game.
    	//Player bank accounts built in game as well. 
    	dealHands();
    	//discard phase;
    	openingPlayers = getOpeningPlayers();
    	// Loop through players, tell UI if player can open or not
    	setOrder();
    	beginBettingPhase();
    	
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
       
    }

    private void setOrder(){
		String firstPlayer = openingPlayers.firstElement();
	    int remainder = 0;
	    boolean firstFound = false;
	    for (String p: players.keySet()){
				if (p.equals(firstPlayer)){
					firstFound = true;
					roundOrder.addFirst(p);
					remainder = 1;
					continue;
				}
				if (firstFound){
					roundOrder.add(remainder, p);
					remainder++;
				}else{
					roundOrder.addLast(p);
				}
			}
    }
    @Override
    protected boolean isFolding(String playerID){   
        return players.get(playerID).isFolding();
    }

    @Override
    protected int getBet(String playerID, int callValue){
        return players.get(playerID).getRaise(0, 0);
    }

    @Override
    public String getWinner(){    
        return super.winner;
    }

    @Override
    protected void addWinnings(String winner, int potSplit){
        
    }
}
