package round.implementation;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import player.framework.Player;
import player.implementation.HumanPlayer;
import pot.framework.Pot;
import pot.framework.PotFactory;
import bank.framework.Bank;
import dealer.framework.Dealer;
import hand.framework.Hand;
import hand.framework.HandFactory;
import hand.implementation.DrawPokerHand;
import hand.implementation.PlayingCard;
import network.framework.Network;

/**
 * This class models a round of draw poker
 * the round is the mediator through which all logical 
 * game mechanisms communicate.
 * @author Ciaran
 *
 */
//Methods are generally ordered in sequence they first appear
public class DrawPokerRound extends RoundTemplate {
	public static final int DISCARD_LIMIT = 3;
	public static int openingBet = 2;
	
	public static String pokerType = "DrawPoker";
	private Vector<String> openingPlayers;
	private LinkedList<String> roundOrder;
	private int currentBet = 0;
	private int highestBet = 0;
	private Map<String, Integer > bettingMap = new HashMap<String, Integer>();
	private Entry<String,Integer> maxEntry = null;
	/**
	 * This round constructor takes in object references from its game thread to operate on
	 * during round life.
	 * @param players
	 * @param dealer
	 * @param bank
	 * @param network
	 */
    public DrawPokerRound(Map<String, Player> players, Dealer dealer, Bank bank, Network network){
        super(players, dealer, bank, network);
        super.pot = PotFactory.getPot(pokerType);
    }
    /**
     * Method runs sequence of events associated with a single round of poker
     */
    public void beginRound(){
        //Sends start sequence to UI
    	//Players Built in Game.
    	//Player bank accounts built in game as well. 
    	network.pushMessageUpdate("New Deal: ");
    	bettingMap.clear();
    	showBank();
    	dealHands();
    	beginDiscardPhase();
    	openingPlayers = getOpeningPlayers();
    	roundOrder = new LinkedList<String>();
    	// Loop through players, tell UI if player can open or not
    	if(!openingPlayers.isEmpty()){
	    	setOrder(); //This is the order for the CURRENT round.
	    	beginBettingPhase();	//controls betting phase.
	    	getWinner();
    	}else {
            network.pushMessageUpdate("Looks like no one could open! Redealing...");
            beginRound();
    	}
    	
    	
    }
    
    /**
     * THis method displays the bank account of all players
     */
    private void showBank(){
    	for (String p: players.keySet()){
    		int funds = bank.getAvailableFunds(p);
    		network.pushMessageUpdate(p + ": I have " + funds + " chips in the bank!");
    	}
    }
    
    /**
     * This method instantiates hands of cards from the deck
     * and assigns them to the players in the player map.
     */
    protected void dealHands(){
    	dealer.reset();
        //Loop through map players, deal out cards until Hand limit is met
    	for (String p: players.keySet()){
    		
    		Hand currentHand = HandFactory.getHand(pokerType);

    		for (int i = 0; i < DrawPokerHand.HAND_LIMIT; i++){
    			currentHand.setCards(dealer.dealNext(), i);
    		}  
    		players.get(p).setHand(currentHand);
    	}
    }
    
    /**
     * This method starts in which all players are asked to choose the cards
     * they will discard and these cards are then replaced
     */
    protected void beginDiscardPhase(){
    	//Loop through players and let them discard.
    	for (String p: players.keySet()){
    		players.get(p).decideDiscarding();
    		discardCards(p);
    	}
    }
    /**
     * This method discards  and replaces all cards in the player discard vector
     * (provided the discard limit hasn't been exceeded)
     * @param playerID
     */
    protected void discardCards(String playerID){
    	int discardSum = 0;
    	
    	while(players.get(playerID).isDiscarding() && (discardSum < 3)){
    		PlayingCard discard = players.get(playerID).discardCard(dealer.dealNext());
    		discardSum++;
    		dealer.returnCard(discard);
    	}
    	network.pushMessageUpdate(playerID + " opted to discard " + discardSum + " cards");
    }
    
    /**
     * This method populates a vector indicating which players can open i.e
     * which players have a high hand or better
     */
    protected Vector<String> getOpeningPlayers(){
        //return ordered vector of players who CAN open. ie have better than high hand.
    	Vector<String> open = new Vector<String>(players.size());
    	for (String p: players.keySet()){
    		if (players.get(p).canOpen()){
    			open.addElement(p);
    			network.pushMessageUpdate(p + " says: I can open");
    		} else { network.pushMessageUpdate(p + " says: I cannot open");};
    	}
        return open;
    }

    /**
     * This method sets the order the players will take turns initially based on
     * the first player to join the table that can open.
     */
    private void setOrder(){
		String firstPlayer = openingPlayers.firstElement();
	    int remainder = 0;
	    boolean firstFound = false;
	   
	    for (String p: players.keySet()){
	    	 //System.out.print(p + "\n");
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
	    network.pushMessageUpdate("Round order: " + roundOrder);
    }

    /**
     * This method queries the players asking if they will fold bet or call
     * until all players that haven't folded have called.
     */
    @Override
    protected void beginBettingPhase(){ 
    	for (String p: players.keySet()){
    		bettingMap.put(p, 0);
    	}
		boolean allCalled = true;
		ListIterator<String> listIterator = roundOrder.listIterator();
		String startingPlayer = roundOrder.getFirst();	//first player must make bet
		Player start = players.get(startingPlayer);
		currentBet = openingBet;
		bank.withdraw(startingPlayer, currentBet);
		pot.addChips(startingPlayer, currentBet);		//add value to pot.
		network.pushMessageUpdate(startingPlayer + " has opened with " + openingBet + " chips");
		bettingMap.put(startingPlayer, currentBet);
		highestBet = currentBet;
		listIterator.next();						//skip first player as already made bet.
		boolean allIn = false;
		while (roundOrder.size() > 1){					//while there is more than one player still playing loop
			allCalled = true;							//Set to true, only becomes false if someone raises.
			while (listIterator.hasNext()){				//Loops through roundOrder until reaches last player.
				callValue();
				String playerName = listIterator.next();
				network.pushMessageUpdate("\n" + playerName + " will need to bet: " + (highestBet - bettingMap.get(playerName) + " to call this round"));
				network.pushMessageUpdate("Current Player: " + playerName);
		    	Player p = players.get(playerName);
		    	p.decideStrategy(this);
			
				if(bank.getAvailableFunds(playerName) < openingBet){
					players.remove(playerName);
					network.pushMessageUpdate(playerName + " has folded");
					listIterator.remove();
					continue;
				}
				
		     		if(p.isFolding()){
		    			//roundOrder.remove(playerName);					//remove from linkedlist as out of round.
		     			network.pushMessageUpdate(playerName + " has folded");
		     			listIterator.remove();
	    				network.pushMessageUpdate(playerName + " has " + bank.getAvailableFunds(playerName) + " in their bank account");
		    			if (roundOrder.size() == 1) { break; };
		    		} else if (p.isCalling()){
		    			int lastBet = bettingMap.get(playerName);			//Value taken from map. Value of what they have bet so far
		    			int callValue = highestBet - lastBet;      //Pot total - Minus what they have bet = what they need to bet to call
		    			bankToPot(playerName, callValue);
		    			bettingMap.put(playerName, lastBet+callValue);		//updates betting map
		    			network.pushMessageUpdate(playerName + " has called");
		    			network.pushMessageUpdate("Current pot value: " + pot.getTotalValue());
	    				network.pushMessageUpdate(playerName + " has " + bank.getAvailableFunds(playerName) + " in their bank account");
		    		} else if (p.isRaising()){
		    			int lastBet = bettingMap.get(playerName);			//Value taken from map. Value of what they have bet so far
		    			int callValue = highestBet - lastBet;		//Pot total - Minus what they have bet = what they need to bet to call
		    			//bankToPot(playerName, callValue);
		    			//bettingMap.put(playerName, lastBet+callValue);		//updates betting map
		    			bankToPot(playerName, callValue);
		    			//int oldBet = currentBet;
		    			currentBet = p.getRaise();							//gets raise value
		    			//currentBet += oldBet;
		    			network.pushMessageUpdate("\n" + playerName + " has raised by " + currentBet);
		    			bankToPot(playerName, currentBet);					//transfers raise from bank to pot
		    			lastBet = bettingMap.get(playerName);				//gets last bet (now equal to pot value)
		    			bettingMap.put(playerName, lastBet+callValue+currentBet);		//updates betting map to raised value.
	    				if(bank.getAvailableFunds(playerName) == 0){
	    					allIn = true;
	    				}
	    				allCalled = false;									//allCall set to false as player has raised.
	    				reOrder(playerName);								//Reorders players, now person who raise is first.
	    				listIterator = roundOrder.listIterator(); 			//reset iterator
	    				//System.out.println("Re order: " + roundOrder);
	    				listIterator.next();								//skip first player as already made bet.
	    				network.pushMessageUpdate("Current pot value: " + pot.getTotalValue());
	    				network.pushMessageUpdate(playerName + " has " + bank.getAvailableFunds(playerName) + " chips in their bank account");
	    				//highestBet = currentBet;
	    				break;
		    	}
		    }
		    //Check to see if everyone has called.
		    if (allCalled == true){
		    	break;	//If all have called then break from while loop. Betting Phase is over.
			    }
	    	}
		
	}
    
    private void callValue(){
    	//highestBet = 0;
    	for(Entry<String,Integer> entry : bettingMap.entrySet()) {
    	    if (maxEntry == null || entry.getValue() > maxEntry.getValue()) {
    	        maxEntry = entry;
    	    }
    	}
    	highestBet = maxEntry.getValue();
    }
    
    /**
     * This method moves money from a players bank account into the pot
     */
    private void bankToPot(String playerName, int amount){
    	if(amount <= bank.getAvailableFunds(playerName)){
    		bank.withdraw(playerName, amount);
    		pot.addChips(playerName, amount);
    		
    	} else {
    		int allin = bank.getAvailableFunds(playerName);
    		bank.withdraw(playerName, allin);
    		pot.addChips(playerName, allin);
    		network.pushMessageUpdate(playerName + " is all in!" + playerName + " put " + allin + " into the pot.");
    	}
    }
    /**
     * This method returns the current call value for the pot for the round.
     * @return callValue
     */
	@Override
	public int getCallValue() {
		return currentBet;
	}
	
    /**
     * This method mutates the set order of play based on which players have folded and which players last raised.
     * @param playerWhoRaised
     */
    private void reOrder(String playerWhoRaised){				//Reorder function for when a player raises.
		String firstPlayer = playerWhoRaised;		//Makes person who raised first in the roundOrder
	    int remainder = 0;										//This means eg:Everyone else following him then calls, the person
	    boolean firstFound = false;								//who called  CANNOT raise again. This ensures that.
	    LinkedList<String> tempReorder = new LinkedList<String>();
	    tempReorder = (LinkedList<String>) roundOrder.clone();	//clones previous order to temp list.
	    roundOrder.clear();										//Clears round order, so new order can be added.
	    ListIterator<String> listIterator = tempReorder.listIterator();
	    while(listIterator.hasNext()){							//will add players to roundOrder in correct new order.
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
    
    /**
     * This method checks if a given player is folding.
     * @param playerID
     * @return isFolding
     */
    protected boolean isFolding(String playerID){   
        return players.get(playerID).isFolding();
    }

    /**
     * This method identifies the round winner and display their name.
     */
    @Override
    public String getWinner(){
    	//Loop through remaining players in roundOrder. Compare hand values.
    	Player winner = null;
    	String winnerName = null;
    	Player temp = null;
    	int handOfWinner = 0;
    	ListIterator<String> handShowIterator = roundOrder.listIterator();
    	while (handShowIterator.hasNext()){
    		String playerName = handShowIterator.next();
    		Player p = players.get(playerName);
    		network.pushMessageUpdate(playerName + "'s hand: " + p.getHand());
    	}
    	ListIterator<String> listIterator = roundOrder.listIterator();
    	while (listIterator.hasNext()){
    		String playerName = listIterator.next();
    		//System.out.println(playerName);
    		Player p = players.get(playerName);
    		int handOfTemp = p.getHand().getGameValue();
    		if (handOfTemp > handOfWinner){
    			winner = p;
    			winnerName = playerName;
    		}
    		handOfWinner = winner.getHand().getGameValue();
    	}
    	addWinnings(winnerName, 0);
    	network.pushMessageUpdate(winnerName + " has won!");
        return super.winner;
    }
 
    /**
     * This method moves the chips from the pot into the bank of the winning player.
     */
    protected void addWinnings(String winner, int potSplit){
        int winnings = pot.getTotalValue();
        network.pushMessageUpdate("Pot total: " + winnings);
        for (String p: players.keySet()){
        	if (p.equals(winner)){
        		bank.deposit(p, winnings);
                network.pushMessageUpdate(p + " now has " + bank.getAvailableFunds(p) + " chips in the bank.");
        	}
        }
    }
	
}
