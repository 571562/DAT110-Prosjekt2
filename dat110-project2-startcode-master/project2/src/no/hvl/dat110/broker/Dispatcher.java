package no.hvl.dat110.broker;

import java.util.Set;
import java.util.Collection;

import no.hvl.dat110.client.Client;
import no.hvl.dat110.common.Logger;
import no.hvl.dat110.common.Stopable;
import no.hvl.dat110.messages.*;
import no.hvl.dat110.messagetransport.Connection;

/**
 * The type Dispatcher.
 */
public class Dispatcher extends Stopable {

	private Storage storage;

    /**
     * Instantiates a new Dispatcher.
     *
     * @param storage the storage
     */
    public Dispatcher(Storage storage) {
		super("Dispatcher");
		this.storage = storage;

	}

	@Override
	public void doProcess() {

		Collection<ClientSession> clients = storage.getSessions();

		Logger.lg(".");
		for (ClientSession client : clients) {

			Message msg = null;

			if (client.hasData()) {
				msg = client.receive();
			}

			if (msg != null) {
				dispatch(client, msg);
			}
		}

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

    /**
     * Dispatch.
     *
     * @param client the client
     * @param msg    the msg
     */
    public void dispatch(ClientSession client, Message msg) {

		MessageType type = msg.getType();

		switch (type) {

		case DISCONNECT:
			onDisconnect((DisconnectMsg) msg);
			break;

		case CREATETOPIC:
			onCreateTopic((CreateTopicMsg) msg);
			break;

		case DELETETOPIC:
			onDeleteTopic((DeleteTopicMsg) msg);
			break;

		case SUBSCRIBE:
			onSubscribe((SubscribeMsg) msg);
			break;

		case UNSUBSCRIBE:
			onUnsubscribe((UnsubscribeMsg) msg);
			break;

		case PUBLISH:
			onPublish((PublishMsg) msg);
			break;

		default:
			Logger.log("broker dispatch - unhandled message type");
			break;

		}
	}

    /**
     * On connect.
     *
     * @param msg        the msg
     * @param connection the connection
     */
// called from Broker after having established the underlying connection
	public void onConnect(ConnectMsg msg, Connection connection) {

		String user = msg.getUser();

		Logger.log("onConnect:" + msg.toString());

		storage.addClientSession(user, connection);

	}

    /**
     * On disconnect.
     *
     * @param msg the msg
     */
// called by dispatch upon receiving a disconnect message
	public void onDisconnect(DisconnectMsg msg) {

		String user = msg.getUser();

		Logger.log("onDisconnect:" + msg.toString());

		storage.removeClientSession(user);

	}

    /**
     * On create topic.
     *
     * @param msg the msg
     */
    public void onCreateTopic(CreateTopicMsg msg) {

		Logger.log("onCreateTopic:" + msg.toString());

		storage.createTopic(msg.getTopic());

	}

    /**
     * On delete topic.
     *
     * @param msg the msg
     */
    public void onDeleteTopic(DeleteTopicMsg msg) {

		Logger.log("onDeleteTopic:" + msg.toString());

		storage.deleteTopic(msg.getTopic());
	}

    /**
     * On subscribe.
     *
     * @param msg the msg
     */
    public void onSubscribe(SubscribeMsg msg) {

		Logger.log("onSubscribe:" + msg.toString());

        storage.addSubscriber(msg.getUser(), msg.getTopic());

	}

    /**
     * On unsubscribe.
     *
     * @param msg the msg
     */
    public void onUnsubscribe(UnsubscribeMsg msg) {

		Logger.log("onUnsubscribe:" + msg.toString());

		storage.removeSubscriber(msg.getUser(), msg.getTopic());

	}

    /**
     * On publish.
     *
     * @param msg the msg
     */
    public void onPublish(PublishMsg msg) {

		Logger.log("onPublish:" + msg.toString());

        Collection<String> subscribers = storage.getSubscribers(msg.getTopic());
        if(subscribers != null) {
            subscribers.stream().forEach(x -> {
                ClientSession session = storage.clients.get(x);
                if(session != null) {
                    session.send(msg);
                }
            });
        }
	}
}
