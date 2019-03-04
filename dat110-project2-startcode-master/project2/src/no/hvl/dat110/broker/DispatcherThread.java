package no.hvl.dat110.broker;

import no.hvl.dat110.common.Logger;
import no.hvl.dat110.common.Stopable;
import no.hvl.dat110.messages.*;
import no.hvl.dat110.messagetransport.Connection;

import java.util.Collection;
import java.util.concurrent.LinkedBlockingQueue;

public class DispatcherThread extends Stopable {


    private Storage storage;
    private ClientSession client;
    private LinkedBlockingQueue<Message> messageQueue;

    public DispatcherThread(Storage storage, ClientSession client, LinkedBlockingQueue<Message> messageQueue) {
        super("DispatcherThread: " + client.getUser());
        this.storage = storage;
        this.client = client;
        this.messageQueue = messageQueue;
    }

    @Override
    public void doProcess() {
        Message msg = null;

        if (client.hasData()) {
            msg = client.receive();
        }

        if (msg != null) {
            dispatch(client, msg);
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

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

    // Passes message to dispatcher for stopping and deleting of threads
    public void onDisconnect(DisconnectMsg msg) {
        messageQueue.add(msg);
    }

    public void onCreateTopic(CreateTopicMsg msg) {

        Logger.log("onCreateTopic:" + msg.toString());

        storage.createTopic(msg.getTopic());
    }

    public void onDeleteTopic(DeleteTopicMsg msg) {

        Logger.log("onDeleteTopic:" + msg.toString());

        storage.deleteTopic(msg.getTopic());
    }

    public void onSubscribe(SubscribeMsg msg) {

        Logger.log("onSubscribe:" + msg.toString());

        storage.addSubscriber(msg.getUser(), msg.getTopic());
    }

    public void onUnsubscribe(UnsubscribeMsg msg) {

        Logger.log("onUnsubscribe:" + msg.toString());

        storage.removeSubscriber(msg.getUser(), msg.getTopic());
    }

    public void onPublish(PublishMsg msg) {

        Logger.log("onPublish:" + msg.toString());

        Collection<String> subscribers = storage.getSubscribers(msg.getTopic());
        if (subscribers != null) {
            subscribers.stream().forEach(x -> {
                ClientSession session = storage.clients.get(x);
                if (session != null) {
                    session.send(msg);
                }
            });
        }

    }
}
