package no.hvl.dat110.broker;

import no.hvl.dat110.messages.Message;
import no.hvl.dat110.messages.MessageUtils;
import no.hvl.dat110.messagetransport.Connection;

/**
 * The type Client session.
 */
public class ClientSession {

	private String user;
	private Connection connection;

    /**
     * Instantiates a new Client session.
     *
     * @param user       the user
     * @param connection the connection
     */
    public ClientSession(String user, Connection connection) {
		this.user = user;
		this.connection = connection;

	}

    /**
     * Disconnect.
     */
    public void disconnect() {

		if (connection != null) {
			connection.close();
		}
	}

    /**
     * Gets user.
     *
     * @return the user
     */
    public String getUser() {
		return user;
	}

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(String user) {
		this.user = user;
	}

    /**
     * Send.
     *
     * @param message the message
     */
    public void send(Message message) {

		MessageUtils.send(connection, message);
	}

    /**
     * Has data boolean.
     *
     * @return the boolean
     */
    public boolean hasData() {

		return connection.hasData();
	}

    /**
     * Receive message.
     *
     * @return the message
     */
    public Message receive() {

		Message msg = MessageUtils.receive(connection);

		return msg;
	}

}
