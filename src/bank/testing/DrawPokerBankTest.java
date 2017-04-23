package bank.testing;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import bank.exceptions.BankAccountNotFoundException;
import bank.implementation.*;

public class DrawPokerBankTest {

	private DrawPokerBank singleTestBank;
	private DrawPokerBank multipleTestBank;
	private DrawPokerBank emptyTestBank;
	private int testFunds = 10;
	
	@Before
	public void setUp() throws Exception {
		Set<String> testSet = new HashSet<String>();
		emptyTestBank = new DrawPokerBank(testSet, testFunds); 
		testSet.add("Player 1");
		singleTestBank = new DrawPokerBank(testSet, testFunds);
		for(int i = 0; i < 3; i++){
			testSet.add("Player " + (i + 2));
		}
		multipleTestBank = new DrawPokerBank(testSet, testFunds);
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Tests Draw Poker Bank Constructor initialization yields
	 * an object as expected for cases where:
	 * - A single element set is sent to the constructor
	 * - An empty set is sent to the constructor
	 * - A multiple element set is sent to the constructor
	 */
	@Test
	public void testDrawPokerBank() {
		assertNotNull(singleTestBank);
		assertEquals("singleTestBank should be of type DrawPokerBank, construction failed",
					 DrawPokerBank.class.toString(),
					 singleTestBank.getClass().toString());
		
		assertNotNull(multipleTestBank);
		assertEquals("multipleTestBank should be of type DrawPokerBank, construction failed",
				     DrawPokerBank.class.toString(),
				     multipleTestBank.getClass().toString());
		
		assertNotNull(emptyTestBank);
		assertEquals("emptyTestBank should be of type DrawPokerBank, construction failed",
				 	 DrawPokerBank.class.toString(),
				 	 emptyTestBank.getClass().toString());
	}

	/**
	 * Tests getAvailable funds for instances where:
	 * -There is no player tied to the given bank account
	 * -There is one player in the bank
	 * -there are multiple players in the bank
	 */
	@Test
	public void testGetAvailableFunds() {
		int funds = 0;
		try{
			emptyTestBank.getAvailableFunds("Player 1");
			fail("Account doesn't exist, an exception should have been thrown");
		}catch(BankAccountNotFoundException e){
		}
		
		try{
			funds = singleTestBank.getAvailableFunds("Player 1");
			assertEquals("Initial funds should be set to 10 in singleTestBank", testFunds, funds);
		}catch(BankAccountNotFoundException e){
			fail("The specified account wasn't found in singleTestBank and an exception was thrown.");
		}
		
		for(int i = 1; i < 5; i++){
			try{
				funds = multipleTestBank.getAvailableFunds("Player " + i);
				assertEquals("Initial funds should be set to 10 in multipleTestBank", testFunds, funds);
			}catch(BankAccountNotFoundException e){
				fail("The specified account wasn't found in singleTestBank and an exception was thrown.");
			}
		}
	}

	/**
	 * Tests withdraw funds for instances where:
	 * -There is no player tied to the given bank account
	 * -There is one player in the bank
	 * -there are multiple players in the bank
	 */
	@Test
	public void testWithdraw() {
		int funds = 0;
		try{
			emptyTestBank.withdraw("Player 1", 5);
			fail("Account doesn't exist, an exception should have been thrown");
		}catch(BankAccountNotFoundException e){
		}
		
		try{
			singleTestBank.withdraw("Player 1", 5);
			funds = singleTestBank.getAvailableFunds("Player 1");
			assertEquals("After withdrawal funds should be 5 in singleTestBank", testFunds - 5, funds);
		}catch(BankAccountNotFoundException e){
			fail("The specified account wasn't found in singleTestBank and an exception was thrown.");
		}
		
		for(int i = 1; i < 5; i++){
			try{
				multipleTestBank.withdraw("Player " + i, 5);
				funds = multipleTestBank.getAvailableFunds("Player " + i);
				assertEquals("After withdrawal funds should be 5 in multipleTestBank", testFunds - 5, funds);
			}catch(BankAccountNotFoundException e){
				fail("The specified account wasn't found in singleTestBank and an exception was thrown.");
			}
		}
	}

	/**
	 * Tests deposit funds for instances where:
	 * -There is no player tied to the given bank account
	 * -There is one player in the bank
	 * -there are multiple players in the bank
	 */
	@Test
	public void testDeposit() {
		int funds = 0;
		try{
			emptyTestBank.deposit("Player 1", 5);
			fail("Account doesn't exist, an exception should have been thrown");
		}catch(BankAccountNotFoundException e){
		}
		
		try{
			singleTestBank.deposit("Player 1", 5);
			funds = singleTestBank.getAvailableFunds("Player 1");
			assertEquals("After deposit funds should be 15 in singleTestBank", testFunds + 5, funds);
		}catch(BankAccountNotFoundException e){
			fail("The specified account wasn't found in singleTestBank and an exception was thrown.");
		}
		
		for(int i = 1; i < 5; i++){
			try{
				multipleTestBank.deposit("Player " + i, 5);
				funds = multipleTestBank.getAvailableFunds("Player " + i);
				assertEquals("After withdrawal funds should be 15 in multipleTestBank", testFunds + 5, funds);
			}catch(BankAccountNotFoundException e){
				fail("The specified account wasn't found in singleTestBank and an exception was thrown.");
			}
		}
	}

}
