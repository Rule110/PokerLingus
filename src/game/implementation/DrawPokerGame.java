package game.implementation;

import java.util.LinkedHashMap;
import java.util.Map;
import bank.framework.Bank;
import bank.framework.BankFactory;
import network.framework.Network;
import network.implementation.LocalNetwork;
import network.implementation.TwitterNetwork;
import player.framework.Player;
import player.framework.PlayerFactory;
import pokerfaice.Parser;
import round.framework.Round;
import round.framework.RoundFactory;
import textupdate.framework.TextUpdate;
import database.framework.Database;
import dealer.framework.Dealer;
import dealer.framework.DealerFactory;
import game.framework.Game;
import game.framework.GameFactory;
import gfxupdate.framework.GfxUpdate;

/**
 * This class models a runnable game of draw poker that can run locally and self contained
 * or as one thread of many in a muli-threaded twitter application.
 * @author Darragh
 *
 */
public class DrawPokerGame extends Game {
	
	public static final String gameType = "DrawPoker";
	private static final int OPPONENT_COUNT = 4;
	
	private static final int START_CHIPS = 40;	//Starting funds for players
	   
    private Network network;
    private Database database;
    private Parser parser;
    private Dealer dealer;
    private Map<String, Player> players;
    private Bank bank;
    
    
    /**
     * THis constructor initializes the game thread instance variables necessary to run the game
     * @param username
     * @param network
     */
    public DrawPokerGame(String username, Network network){
        this.network = network;
        this.parser = new Parser("Text");
        this.dealer = DealerFactory.getDealer(gameType);
        this.players = new LinkedHashMap<String, Player>();
        pushMessageUpdate("Welcome " + username + ", enjoy your game of " + gameType);
       
        for (int i = 0; i < OPPONENT_COUNT; i++){
            String name = Parser.getName();
            if(!players.containsKey(name)){
            	this.players.put(name, PlayerFactory.getPlayer("Automated", this, name));
            }else{
            	i--;
            }
        }
        this.players.put(username, PlayerFactory.getPlayer("Human", this, username));
        
        this.bank = BankFactory.getBank("DrawPoker", players.keySet(), START_CHIPS);
        
    }
    /**
     * Method runs a sequential game of draw poker, instantiating new rounds as necessary
     */
    @Override
    public void run(){
    	while(players.size() > 1){
    		Round currentRound = RoundFactory.getRound(gameType, players, dealer, bank, network);
    		currentRound.beginRound();
    		if(players.size() > 1){
    			if(quitPrompt()){
        			break;
        		}
    		}
    	}
    	network.pushMessageUpdate("GAME HAS ENDED");
    	return;
    }
    
    /**
     * Method pushes a text update object to the network attached to the game.
     */
    @Override
    public void pushTextUpdate(TextUpdate update){
        network.sendTextUpdate(update);
    }
   
    /**
     * Method waits one input to arrive in the input stream(in instances of remote input)
     * @return Scanned input
     */
    @Override
    public synchronized String getMessageUpdate(){
    	if(network.getClass() == TwitterNetwork.class){
			try {
				//System.out.println("Waiting:" + Thread.currentThread());
				this.wait();
				//System.out.println("Going");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    	return network.getMessageUpdate();
    }
    
    
    /**
     * Method Captures game input to the input stream and wakes up the game thread.
     */
    @Override
    public synchronized void captureMessageUpdate(String newMessage){
    	network.captureMessageUpdate(newMessage);
    	this.notify();
    }
    
    /**
     * Method pushes a graphic update object to the network attached to the game.
     */
    @Override
    public void pushGfxUpdate(GfxUpdate update){
        network.pushGFxUpdate(update);
    }

    /**
     * Method pushes a string update object to the network attached to the game.
     */
	@Override
	public void pushMessageUpdate(String update) {
		network.pushMessageUpdate(update);
	}
	
	/**
	 * Method returns the games database
	 */
	public Database getDatabase(){
	    return this.database;
	}
	
	/**
	 * Method returns the games parser.
	 * @return parser
	 */
	public Parser getParser(){
	    return this.parser;
	}
	
	/**
	 * Main method stub used for testing purposes
	 * @param args
	 */
	public static void main(String[] args){
		Network testNet= new LocalNetwork("Darragh");
		Game testGame = GameFactory.getGame(DrawPokerGame.gameType, "Darragh", testNet);
		testGame.start();
	}
	
	/**
	 * This method accepts a character input from the user indicating whether they 
	 * wish to quit or continue playing. Invalid responses are rejected and the user is re-prompted
	 * @return isQuiting
	 */
	private boolean quitPrompt(){
		boolean isQuiting = false;
		boolean isValid     = false;
		String quit = "";
		
		while (!isValid){
	    	pushMessageUpdate("Play another round? Type q to quit the game, or type y to continue...");
	    	quit = getMessageUpdate();
	    	if(quit.toLowerCase().equals("q")){
	    		isQuiting =  isValid  = true;
	    	}else if(quit.toLowerCase().equals("y")){
	    		isQuiting =  !(isValid = true);
	    	}else{
	    		pushMessageUpdate("Invalid input, please enter (q/y)\n");
	    	}
		}
		return isQuiting;
	}
}