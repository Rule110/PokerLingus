package pot.testing;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pot.framework.Pot;
import pot.implementation.DrawPokerPot;

public class DrawPokerPotTest {

	private Pot testPot;
	@Before
	public void setUp() throws Exception {
		testPot = new DrawPokerPot();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddChips() {
		
		
	}

	@Test
	public void testRemoveChips() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTotalValue() {
		fail("Not yet implemented");
	}

	@Test
	public void testEnteringSplitPot() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPotSplit() {
		fail("Not yet implemented");
	}

}
