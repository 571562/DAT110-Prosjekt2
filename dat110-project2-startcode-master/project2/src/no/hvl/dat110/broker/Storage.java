package no.hvl.dat110.broker;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import no.hvl.dat110.common.Logger;
import no.hvl.dat110.messagetransport.Connection;

/**
 * The type Storage.
 */
public class Storage {

    /**
     * The Subscriptions.
     */
    protected ConcurrentHashMap<String, Set<String>> subscriptions;
    /**
     * The Clients.
     */
    protected ConcurrentHashMap<String, ClientSession> clients;

    /**
     * Instantiates a new Storage.
     */
    public Storage() {
		subscriptions = new ConcurrentHashMap<String, Set<String>>();
		clients = new ConcurrentHashMap<String, ClientSession>();
	}

    /**
     * Gets sessions.
     *
     * @return the sessions
     */
    public Collection<ClientSession> getSessions() {
		return clients.values();
	}

    /**
     * Gets topics.
     *
     * @return the topics
     */
    public Set<String> getTopics() {

		return subscriptions.keySet();

	}

    /**
     * Gets session.
     *
     * @param user the user
     * @return the session
     */
    public ClientSession getSession(String user) {

		ClientSession session = clients.get(user);

		return session;
	}

    /**
     * Gets subscribers.
     *
     * @param topic the topic
     * @return the subscribers
     */
    public Set<String> getSubscribers(String topic) {

		return (subscriptions.get(topic));

	}

    /**
     * Add client session.
     *
     * @param user       the user
     * @param connection the connection
     */
    public void addClientSession(String user, Connection connection) {

		// TODO: add corresponding client session to the storage
		
		throw new RuntimeException("not yet implemented");
		
	}

    /**
     * Remove client session.
     *
     * @param user the user
     */
    public void removeClientSession(String user) {

		// TODO: remove client session for user from the storage

		throw new RuntimeException("not yet implemented");
		
	}

    /**
     * Create topic.
     *
     * @param topic the topic
     */
    public void createTopic(String topic) {

		// TODO: create topic in the storage

		throw new RuntimeException("not yet implemented");
	
	}

    /**
     * Delete topic.
     *
     * @param topic the topic
     */
    public void deleteTopic(String topic) {

		// TODO: delete topic from the storage

		throw new RuntimeException("not yet implemented");
		
	}

    /**
     * Add subscriber.
     *
     * @param user  the user
     * @param topic the topic
     */
    public void addSubscriber(String user, String topic) {

		// TODO: add the user as subscriber to the topic
		
		throw new RuntimeException("not yet implemented");
		
	}

    /**
     * Remove subscriber.
     *
     * @param user  the user
     * @param topic the topic
     */
    public void removeSubscriber(String user, String topic) {

		// TODO: remove the user as subscriber to the topic

		throw new RuntimeException("not yet implemented");
	}
}
