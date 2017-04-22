package round.implementation;

import java.util.LinkedList;
import java.util.ListIterator;
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
    
    public void beginRound(){
        //Sends start sequence to UI
    	//Players Built in Game.
    	//Player bank accounts built in game as well. 
    	dealHands();
    	//discard phase;
    	openingPlayers = getOpeningPlayers();
    	// Loop through players, tell UI if player can open or not
    	setOrder(); //This is the order for the CURRENT round.
    	beginBettingPhase();	//controls betting phase.
    	getWinner();
    }
    
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
    
    protected void beginDiscardPhase(){
    	for (String p: players.keySet()){
    		getDiscardedCards(p);
    	}
    }
    
    protected void getDiscardedCards(String playerID){
        //Darragh will do
    }
    
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
    
//<<<<<<< HEAD
    @Override
    protected void beginBettingPhase(){ 
		boolean allCalled = true;
		ListIterator<String> listIterator = roundOrder.listIterator();
		String startingPlayer = roundOrder.getFirst();	//first player must make bet
		Player start = players.get(startingPlayer);
		int currentBet = start.getOpeningBet();
		listIterator.next();							//skip first player as already made bet.
		while (roundOrder.size() > 1){					//while there is more than one player still playing loop
			allCalled = true;
			while (listIterator.hasNext()){				//Loops through roundOrder until reaches last player.
		    	String playerName = listIterator.next();
		    	Player p = players.get(playerName);
		     		if(p.isFolding()){
		    			roundOrder.remove(p);		//remove from linkedlist as out of round.
		    		} else if (p.isCalling()){
		    			bank.withdraw(playerName, currentBet);	//subtract value from player bank account.
		    			pot.addChips(playerName, currentBet);	//add value to pot.
		    		} else if (p.isRaising()){
//=======
//    protected void beginBettingPhase(){   	
//    	ListIterator<String> listIterator = roundOrder.listIterator();
//    	String startingPlayer = roundOrder.getFirst();	//first player must make bet
//    	Player start = players.get(startingPlayer);
//    	int currentBet = start.getOpeningBet();
//    	//boolean decisionChecker = false;
//    	//while (decisionChecker != true){
//    	//decisionChecker = true;
//	    	while (listIterator.hasNext()){
//	    		String playerName = listIterator.next();
//	    		Player p = players.get(playerName);
//	    		if (isFolding(playerName) == false){
//	    			if(p.isFolding() == true){
//	    				//remove from linkedlist
//	    				//set isFolding(playerName) true);	    				
//	    			} else if (p.isCalling() == true){
//	    				//subtract value from player bank account.
//	    				//add value to pot.
//	    				//break
//	    			} else if (p.isRaising() == true){
//>>>>>>> branch 'Development' of git@git.ucd.ie:14430762/PokerLingus.git
	    				currentBet = p.getRaise();
	    				bank.withdraw(playerName, currentBet);	//subtract value from player bank account.
	    				pot.addChips(playerName, currentBet);
	    				allCalled = false;						//allCall set to false as player has raised.
	    				//reOrder round
	    				reOrder(p);	//Reorders players, now person who raise is first.
	    				break;
		    	}
		    }
		    //Check to see if everyone has called.
		    if (allCalled == true){
		    	break;	//If all have called then break from while loop. Betting Phase over.
			    }
	    	}
		
	}
    
    private void reOrder(Player playerWhoRaised){						//Reorder function for when a player raises.
		String firstPlayer = playerWhoRaised.toString();
	    int remainder = 0;
	    boolean firstFound = false;
	    LinkedList<String> tempReorder = new LinkedList<String>();
	    tempReorder = (LinkedList<String>) roundOrder.clone();
	    roundOrder.clear();
	    ListIterator<String> listIterator = tempReorder.listIterator();
	    while(listIterator.hasNext()){
	    		String p = listIterator.next();
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
    
    protected boolean isFolding(String playerID){   
        return players.get(playerID).isFolding();
    }
    
    public int getCallValue(){
        //Ciaran implement?
        return 0;
    }
//<<<<<<< HEAD

    @Override
    public String getWinner(){
    	//Loop through remaining players in roundOrder. Compare hand values.
    	Player winner = null;
    	Player temp = null;
    	int handOfWinner = 0;
    	ListIterator<String> listIterator = roundOrder.listIterator();
    	while (listIterator.hasNext()){
    		String playerName = listIterator.next();
    		Player p = players.get(playerName);
    		int handOfTemp = p.getHand().getGameValue();
    		if (handOfTemp > handOfWinner){
    			winner = p;
    		}
    		handOfWinner = winner.getHand().getGameValue();
    	}
    	String winnerName = winner.toString();
    	addWinnings(winnerName, 0);
//=======
    
//    public String getWinner(){    
//>>>>>>> branch 'Development' of git@git.ucd.ie:14430762/PokerLingus.git
        return super.winner;
    }
    
    protected void addWinnings(String winner, int potSplit){
        int winnings = pot.getTotalValue();
        for (String p: players.keySet()){
        	if (p.equals(winner)){
        		bank.deposit(p, winnings);
        	}
        }
    }
}
//test push