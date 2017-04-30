package round.implementation;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Vector;

import bank.framework.Bank;
import bank.framework.BankFactory;
import database.framework.Database;
import dealer.framework.Dealer;
import dealer.framework.DealerFactory;
import game.framework.Game;
import game.implementation.DrawPokerGame;
import junit.framework.TestCase;
import network.framework.Network;
import network.implementation.LocalNetwork;
import player.framework.Player;
import player.framework.PlayerFactory;
import pot.framework.PotFactory;
import round.framework.Round;

public class RoundTesting extends TestCase {
	
	private String gameType = "DrawPoker";
    private Network network;
    private Database database;
    private Dealer dealer;
    private Map<String, Player> players;
    private Bank bank;
    private static int START_CHIPS = 10;
    

	public static String pokerType = "DrawPoker";
	private Vector<String> openingPlayers;
	private LinkedList<String> roundOrder;
    
	protected int i;
	
	protected void setup(){
	}
	
	public void testAdd(){
		this.players = new LinkedHashMap<String, Player>();
        String nameP1 = "Kevin";
        String nameP2 = "Billy";
        String nameP3 = "Sasha";
        Network networkTest = new LocalNetwork(nameP1);
        Game gameTest = new DrawPokerGame(nameP1, networkTest);
        this.players.put(nameP1, PlayerFactory.getPlayer("Human", gameTest, nameP1)); 
        this.players.put(nameP2, PlayerFactory.getPlayer("Human", gameTest, nameP2)); 
        this.players.put(nameP3, PlayerFactory.getPlayer("Human", gameTest, nameP3));       
        this.bank = BankFactory.getBank(gameType, players.keySet(), START_CHIPS);
		//Map<String, Player> roundplayers = null;
        this.dealer = DealerFactory.getDealer(gameType);
		//Dealer dealer = new ;
		Round testRound = new DrawPokerRound(players, dealer, bank);
		assertTrue(bank.getAvailableFunds(nameP1) == 10);
		testRound.beginRound();
		assertTrue(players.size() > 0);
		assertTrue(players.get(nameP1).getHand().size() == 5);
		System.out.println("Kevin: " + bank.getAvailableFunds(nameP1));
		System.out.println("Billy: " + bank.getAvailableFunds(nameP2));
		System.out.println("Sasha: " + bank.getAvailableFunds(nameP3));
	}
	
	public void testBasic(){
		assertTrue(1==1);
	}
	
}

//test
