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

public class DrawPokerGame extends Game {
	
	public static final String gameType = "DrawPoker";
	private static final int OPPONENT_COUNT = 4;
    private Network network;
    private Database database;
    private Parser parser;
    private Dealer dealer;
    private Map<String, Player> players;
    private Bank bank;
    private static int START_CHIPS = 10;
    
    
    public DrawPokerGame(String username, Network network){
        this.network = network;
        this.parser = new Parser("Text");
        this.dealer = DealerFactory.getDealer(gameType);
        this.players = new LinkedHashMap<String, Player>();
        pushMessageUpdate("Welcome " + username + ", enjoy your game of " + gameType);
       
        for (int i = 0; i < OPPONENT_COUNT; i++){
            String name = Parser.getName();
            this.players.put(name, PlayerFactory.getPlayer("Automated", this, name));
        }
        this.players.put(username, PlayerFactory.getPlayer("Human", this, username));
        
        this.bank = BankFactory.getBank("DrawPoker", players.keySet(), START_CHIPS);
        
    }
    
    public void run(){
    	while(players.size() > 1){
    		Round currentRound = RoundFactory.getRound(gameType, players, dealer, bank, network);
    		currentRound.beginRound();
    	}
    	network.pushMessageUpdate("GAME HAS ENDED");
    	return;
    }
    public void pushTextUpdate(TextUpdate update){
        network.sendTextUpdate(update);
    }
    
    public synchronized String getMessageUpdate(){
    	if(network.getClass() == TwitterNetwork.class){
			try {
				//System.out.println("Waiting:" + Thread.currentThread());
				this.wait();
				//System.out.println("Going");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	return network.getMessageUpdate();
    }
    
    public synchronized void captureMessageUpdate(String newMessage){
    	System.out.println("Capture start" + Thread.currentThread());
    	System.out.println("Game Thread" + this);
    	network.captureMessageUpdate(newMessage);
    	System.out.println("Capture finish");
    	this.notify();
    }
    
    public void pushGfxUpdate(GfxUpdate update){
        network.pushGFxUpdate(update);
    }

	@Override
	public void pushMessageUpdate(String update) {
		network.pushMessageUpdate(update);
	}
	
	public Database getDatabase(){
	    return this.database;
	}
	
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
}