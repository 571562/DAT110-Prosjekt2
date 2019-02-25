package no.hvl.dat110.client;

import no.hvl.dat110.messages.*;
import no.hvl.dat110.messagetransport.Connection;
import no.hvl.dat110.messagetransport.MessagingClient;

/**
 * The type Client.
 */
public class Client extends Thread {

	private MessagingClient client;
	private Connection connection;
	private String user;

    /**
     * Instantiates a new Client.
     *
     * @param user   the user
     * @param server the server
     * @param port   the port
     */
    public Client(String user, String server, int port) {
		client = new MessagingClient(server, port);
		this.user = user;
	}

	private void send(Message msg) {

		connection.send(MessageUtils.toTransportMessage(msg));

	}

    /**
     * Receive message.
     *
     * @return the message
     */
    public Message receive() {

		return MessageUtils.fromTransportMessage(connection.receive());

	}

    /**
     * Connect boolean.
     *
     * @return the boolean
     */
    public boolean connect() {

		boolean connected = false;

		connection = client.connect();

		ConnectMsg msg = new ConnectMsg(user);

		if (connection != null) {

			send(msg);
			connected = true;

		}

		return connected;
	}

    /**
     * Disconnect.
     */
    public void disconnect() {

		DisconnectMsg msg = new DisconnectMsg(user);

		send(msg);

		connection.close();

	}

    /**
     * Subscribe.
     *
     * @param topic the topic
     */
    public void subscribe(String topic) {

		SubscribeMsg msg = new SubscribeMsg(user, topic);

		send(msg);

	}

    /**
     * Unsubscribe.
     *
     * @param topic the topic
     */
    public void unsubscribe(String topic) {

		UnsubscribeMsg msg = new UnsubscribeMsg(user, topic);

		send(msg);

	}

    /**
     * Publish.
     *
     * @param topic   the topic
     * @param message the message
     */
    public void publish(String topic, String message) {

		PublishMsg msg = new PublishMsg(user, topic, message);

		send(msg);

	}

    /**
     * Create topic.
     *
     * @param topic the topic
     */
    public void createTopic(String topic) {

		CreateTopicMsg msg = new CreateTopicMsg(user, topic);

		send(msg);

	}

    /**
     * Delete topic.
     *
     * @param topic the topic
     */
    public void deleteTopic(String topic) {

		DeleteTopicMsg msg = new DeleteTopicMsg(user, topic);

		send(msg);

	}

}
