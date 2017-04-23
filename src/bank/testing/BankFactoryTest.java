package bank.testing;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import bank.framework.Bank;
import bank.framework.BankFactory;
import bank.implementation.DrawPokerBank;

public class BankFactoryTest {

	String testBankType;
	@Before
	public void setUp() throws Exception {
		Bank testBank = BankFactory.getBank("DrawPoker", new HashSet<String>(), 0);
		testBankType = testBank.getClass().getName();
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Method tests bank factory implementation injection where:
	 * - Type corresponds to Valid type
	 * - Type corresponds to Invalid type
	 */
	@Test
	public void testGetBank() {
		assertEquals(DrawPokerBank.class.getName(), testBankType);
		
		try{
			BankFactory.getBank("",  new HashSet<String>(), 0);
			fail("Factory expected to through runtime acception on invalid type specification");
		}catch(RuntimeException e){
			
		}
	}

}
