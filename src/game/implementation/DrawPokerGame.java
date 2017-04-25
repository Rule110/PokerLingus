package game.implementation;

import java.util.LinkedHashMap;
import java.util.Map;

import bank.framework.Bank;
import bank.framework.BankFactory;
import network.framework.Network;

import player.framework.Player;
import player.framework.PlayerFactory;
import round.framework.Round;
import round.framework.RoundFactory;
import textupdate.framework.TextUpdate;
import database.framework.Database;
import dealer.framework.Dealer;
import dealer.framework.DealerFactory;
import game.framework.Game;
import gfxupdate.framework.GfxUpdate;

public class DrawPokerGame extends Game {
	private String gameType = "DrawPoker";
    private Network network;
    private Database database;
    private Dealer dealer;
    private Map<String, Player> players;
    private Bank bank;
    private static int START_CHIPS = 10;
    
    public DrawPokerGame(String username, Network network){
        this.network = network;
        this.dealer = DealerFactory.getDealer(gameType);
        this.players = new LinkedHashMap<String, Player>();
        pushMessageUpdate("Welcome to pokerFAIce, enjoy your game of " + gameType);
       
        for (int i = 0; i < 4; i++){
            this.players.put("AI" + i, PlayerFactory.getPlayer("Automated", this));
        }
        this.players.put(username, PlayerFactory.getPlayer("Human", this));
        
        this.bank = BankFactory.getBank("DrawPoker", players.keySet(), START_CHIPS);
    }
    
    public void run(){
    	while(players.size() > 1){
    		Round currentRound = RoundFactory.getRound(gameType, players, dealer, bank);
    		currentRound.beginRound();
    	}
    }
    public void pushTextUpdate(TextUpdate update){
        network.sendTextUpdate(update);
    }
    
    public String getMessageUpdate(){
    	return network.getMessageUpdate();
    }
    
    public void captureMessageUpdate(String newMessage){
    	network.captureMessageUpdate(newMessage);
    }
    
    public void pushGfxUpdate(GfxUpdate update){
        
    }

	@Override
	public void pushMessageUpdate(String update) {
		network.pushMessageUpdate(update);
	}
	
	public Database getDatabase(){
	    return this.database;
	}
	
	/**
	 * Implements getStartChips for Game interface 
	 *     using DrawPokerGame constant START_CHIPS
	 */
	public Integer getStartChips(){
	    return DrawPokerGame.START_CHIPS;
	}
}