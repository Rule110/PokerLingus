package round.implementation;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
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

public class DrawPokerRound extends RoundTemplate {
	
	public static String pokerType = "DrawPoker";
	private Vector<String> openingPlayers;
	private LinkedList<String> roundOrder;
	public static int openingBet = 2;
	
    public DrawPokerRound(Map<String, Player> players, Dealer dealer, Bank bank){
        super(players, dealer, bank);
        super.pot = PotFactory.getPot(pokerType);
    }
    
    public void beginRound(){
        //Sends start sequence to UI
    	//Players Built in Game.
    	//Player bank accounts built in game as well. 
    	dealHands();
    	beginDiscardPhase();
    	openingPlayers = getOpeningPlayers();
    	roundOrder = new LinkedList<String>();
    	// Loop through players, tell UI if player can open or not
    	if(!openingPlayers.isEmpty()){
	    	setOrder(); //This is the order for the CURRENT round.
	    	beginBettingPhase();	//controls betting phase.
	    	getWinner();
    	}
    }
    
    protected void dealHands(){
    	dealer.reset();
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
    	//Loop through players and let them discard.
    	for (String p: players.keySet()){
    		getDiscardedCards(p);
    	}
    }
    
    protected void getDiscardedCards(String playerID){
    	 for (String p: players.keySet()){
    		 	Vector<Integer> cardPositions = null;
//				if (p.equals(playerID)){
//					Player currentPlayer = players.get(p);
//					System.out.println(currentPlayer.getHand());
//					System.out.println("Are you Discarding?");	//First ask if they want to discard
					//boolean check = currentPlayer;
					//if(check){
						//System.out.println("How many cards (1 to 3): ");
						//user please enter up to 1 to 3 card positions.
						//for (int i = 0; i < input; i++){
						//	System.out.println("Card Position: ");
						// 	input = scanner;
						//	cardPositions.insertElementAt(input, i);
						//}
						//set positions in to discardindicies vector
						//if(currentPlayer.isDiscarding()){	//if discardindicies is not empty
						//	int cardPosition = 				//get next
						//	currentPlayer.discardCard(replacement);
						//}
					//}
//				}
    	 }
    }
    
    protected Vector<String> getOpeningPlayers(){
        //return ordered vector of players who CAN open. ie have better than high hand.
    	Vector<String> open = new Vector<String>(players.size());
    	for (String p: players.keySet()){
    		if (players.get(p).canOpen()){
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
	    	 System.out.print(p + "\n..");
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
	    System.out.println("Round order: " + roundOrder);
    }

    @Override
    protected void beginBettingPhase(){ 
		boolean allCalled = true;
		ListIterator<String> listIterator = roundOrder.listIterator();
		String startingPlayer = roundOrder.getFirst();	//first player must make bet
		Player start = players.get(startingPlayer);
		int currentBet = openingBet;
		bank.withdraw(startingPlayer, currentBet);
		pot.addChips(startingPlayer, currentBet);		//add value to pot.
		listIterator.next();						//skip first player as already made bet.
		while (roundOrder.size() > 1){					//while there is more than one player still playing loop
			allCalled = true;							//Set to true, only becomes false if someone raises.
			while (listIterator.hasNext()){				//Loops through roundOrder until reaches last player.
				String playerName = listIterator.next();
				System.out.println("Current Player: " + playerName);
		    	Player p = players.get(playerName);
		    		p.decideStrategy(this);
		     		if(p.isFolding()){
		    			//roundOrder.remove(playerName);					//remove from linkedlist as out of round.
		     			listIterator.remove();
		    			if (roundOrder.size() == 1) { break; };
		    		} else if (p.isCalling()){
		    			bank.withdraw(playerName, currentBet);	//subtract value from player bank account.
		    			pot.addChips(playerName, currentBet);	//add value to pot.
		    		} else if (p.isRaising()){
		    			bank.withdraw(playerName, currentBet);
		    			pot.addChips(playerName, currentBet);
		    			currentBet = p.getRaise();
	    				bank.withdraw(playerName, currentBet);	//subtract value from player bank account.
	    				pot.addChips(playerName, currentBet);
	    				allCalled = false;						//allCall set to false as player has raised.
	    				reOrder(playerName);								//Reorders players, now person who raise is first.
	    				listIterator = roundOrder.listIterator(); //reset iterator
	    				System.out.println("Re order: " + roundOrder);
	    				listIterator.next();						//skip first player as already made bet.
	    				break;
		    	}
		    }
		    //Check to see if everyone has called.
		    if (allCalled == true){
		    	System.out.println("test");
		    	break;	//If all have called then break from while loop. Betting Phase over.
			    }
	    	}
		
	}
    
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
    
    protected boolean isFolding(String playerID){   
        return players.get(playerID).isFolding();
    }
    
    public int getCallValue(){
        //Ciaran implement?
        return 0;
    }

    @Override
    public String getWinner(){
    	//Loop through remaining players in roundOrder. Compare hand values.
    	Player winner = null;
    	String winnerName = null;
    	Player temp = null;
    	int handOfWinner = 0;
    	ListIterator<String> listIterator = roundOrder.listIterator();
    	while (listIterator.hasNext()){
    		String playerName = listIterator.next();
    		System.out.println(playerName);
    		Player p = players.get(playerName);
    		int handOfTemp = p.getHand().getGameValue();
    		if (handOfTemp > handOfWinner){
    			winner = p;
    			winnerName = playerName;
    		}
    		handOfWinner = winner.getHand().getGameValue();
    	}
    	//winner = players.get(winner);
    	System.out.println("Winner: " + winnerName);
    	addWinnings(winnerName, 0);
        return super.winner;
    }
    
    protected void addWinnings(String winner, int potSplit){
        int winnings = pot.getTotalValue();
        for (String p: players.keySet()){
        	if (p.equals(winner)){
        		System.out.println("winnings:" + winnings);
        		bank.deposit(p, winnings);
        	}
        }
    }
}
