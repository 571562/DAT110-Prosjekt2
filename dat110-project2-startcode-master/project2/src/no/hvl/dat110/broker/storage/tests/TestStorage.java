package no.hvl.dat110.broker.storage.tests;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import no.hvl.dat110.broker.ClientSession;
import no.hvl.dat110.broker.Storage;
import no.hvl.dat110.messages.ConnectMsg;
import no.hvl.dat110.messages.MessageUtils;

/**
 * The type Test storage.
 */
public class TestStorage {

	private Storage storage;
	private static String TESTUSER = "testuser";
	private static String TESTTOPIC = "testtopic";

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
	public void setUp() throws Exception {
		storage = new Storage();
	}

    /**
     * Tear down.
     *
     * @throws Exception the exception
     */
    @After
	public void tearDown() throws Exception {
	}

    /**
     * Testadd client session.
     */
    @Test
	public void testaddClientSession() {
		
		storage.addClientSession(TESTUSER, null);
		
		assertEquals(storage.getSessions().size(),1);
		
		assertNotEquals(storage.getSession(TESTUSER),null);
	
	}

    /**
     * Testremove client session.
     */
    @Test
	public void testremoveClientSession() {
		
		storage.addClientSession(TESTUSER, null);
		
		assertEquals(storage.getSessions().size(),1);
		
		assertNotEquals(storage.getSession(TESTUSER),null);

		storage.removeClientSession(TESTUSER);
	
		assertEquals(storage.getSessions().size(),0);
		
		assertEquals(storage.getSession(TESTUSER),null);
	}

    /**
     * Testcreate topic.
     */
    @Test
	public void testcreateTopic () {
		
		storage.createTopic(TESTTOPIC);
		
		Set<String> topics = storage.getTopics();
		
		assertEquals(topics.size(),1);

		assertTrue(topics.contains(TESTTOPIC));
	}

    /**
     * Testdelete topic.
     */
    @Test
	public void testdeleteTopic () {
		
		storage.createTopic(TESTTOPIC);
		
		storage.deleteTopic(TESTTOPIC);
		
		assertEquals(storage.getTopics().size(),0);
		
	}

    /**
     * Testadd subscriber.
     */
    @Test
	public void testaddSubscriber () {
		
		storage.createTopic(TESTTOPIC);
		
		storage.addSubscriber(TESTUSER, TESTTOPIC);
		
		Set<String> subscribers = storage.getSubscribers(TESTTOPIC);
		
		assertEquals(subscribers.size(),1);
		
		assertTrue(subscribers.contains(TESTUSER));
		
	}

    /**
     * Testadd subscribers.
     */
    @Test
	public void testaddSubscribers () {
		
		String TESTUSER1 = TESTUSER+"1";
		String TESTUSER2 = TESTUSER+"2";
		
		storage.createTopic(TESTTOPIC);
		storage.createTopic(TESTTOPIC+"1");
		
		storage.addSubscriber(TESTUSER1, TESTTOPIC);
		storage.addSubscriber(TESTUSER2, TESTTOPIC);
		
		Set<String> subscribers = storage.getSubscribers(TESTTOPIC);
		
		assertEquals(subscribers.size(),2);
		
		assertTrue(subscribers.contains(TESTUSER1));
		assertTrue(subscribers.contains(TESTUSER2));
		
	}

    /**
     * Testremove subscribers.
     */
    @Test
	public void testremoveSubscribers () {
	
		String TESTUSER1 = TESTUSER+"1";
		String TESTUSER2 = TESTUSER+"2";
		
		storage.createTopic(TESTTOPIC);
		
		storage.addSubscriber(TESTUSER1, TESTTOPIC);
		storage.addSubscriber(TESTUSER2, TESTTOPIC);
		
		storage.removeSubscriber(TESTUSER2, TESTTOPIC);
		
		Set<String> subscribers = storage.getSubscribers(TESTTOPIC);
		
		assertEquals(subscribers.size(),1);
		
		assertTrue(subscribers.contains(TESTUSER1));
		assertFalse(subscribers.contains(TESTUSER2));
		
	}
	
}
