package database.testing;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import database.framework.DatabaseFactory;
import database.implementation.MongoDbDatabase;

public class DatabaseFactoryTest {

	private static final String MONGODB = "MongoDb";
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetDatabase() {
		assertEquals(MongoDbDatabase.class, DatabaseFactory.getDatabase(MONGODB).getClass());
	}
	
}
