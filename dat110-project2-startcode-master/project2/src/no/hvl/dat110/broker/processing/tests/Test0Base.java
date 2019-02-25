package no.hvl.dat110.broker.processing.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import no.hvl.dat110.broker.Broker;
import no.hvl.dat110.broker.Dispatcher;
import no.hvl.dat110.broker.Storage;

/**
 * The type Test 0 base.
 */
public abstract class Test0Base {

    /**
     * The Dispatcher.
     */
    protected Dispatcher dispatcher;
    /**
     * The Broker.
     */
    protected Broker broker;
    /**
     * The Storage.
     */
    protected Storage storage;

    /**
     * The Broker testport.
     */
    protected int BROKER_TESTPORT = 8080;
    /**
     * The Broker testhost.
     */
    protected String BROKER_TESTHOST = "localhost";

    /**
     * The Runtime.
     */
    protected int RUNTIME = 10000; // time to allow test to execute

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
	public void setUp() throws Exception {
		
		storage = new Storage();
		dispatcher = new Dispatcher(storage);
		broker = new Broker(dispatcher,BROKER_TESTPORT);
		
		dispatcher.start();
		broker.start();
		
		// allow broker to reaching waiting for incoming connections
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

    /**
     * Tear down.
     *
     * @throws Exception the exception
     */
    @After
	public void tearDown() throws Exception {
		
		try {
			Thread.sleep(10000); // let the system run for a while
			broker.join();
			dispatcher.doStop();
			dispatcher.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

    /**
     * Test.
     */
    @Test
	public void test() {
		fail("Not yet implemented");
	}

}
