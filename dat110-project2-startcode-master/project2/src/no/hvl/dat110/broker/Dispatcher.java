package no.hvl.dat110.broker;

import java.util.HashMap;
import java.util.Set;
import java.util.Collection;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import no.hvl.dat110.common.Logger;
import no.hvl.dat110.common.Stopable;
import no.hvl.dat110.messages.*;
import no.hvl.dat110.messagetransport.Connection;

public class Dispatcher extends Stopable {

	private Storage storage;
	private HashMap<String, DispatcherThread> threads;
	private LinkedBlockingQueue<Message> messagesQueue;

	public Dispatcher(Storage storage) {
		super("Dispatcher");
		this.storage = storage;
		this.threads = new HashMap<>();
		this.messagesQueue = new LinkedBlockingQueue<>();
	}

	@Override
	public void doProcess() {

		Collection<ClientSession> clients = storage.getSessions();

		Logger.lg(".");

		Message msg = null;
		try {
			msg = messagesQueue.poll(2000, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (msg != null) {
			ClientSession client = storage.clients.get(msg.getUser());
			if (client != null) {
				dispatch(client, msg);
			}
		}
	}

	// Dispatcher handles only connect and disconnect, other message are handled by dispatcherThreads
	public void dispatch(ClientSession client, Message msg) {

		MessageType type = msg.getType();

		switch (type) {

			case DISCONNECT:
				onDisconnect((DisconnectMsg) msg);
				break;
		}
	}

	// called from Broker after having established the underlying connection
	public void onConnect(ConnectMsg msg, Connection connection) {

		String user = msg.getUser();

		Logger.log("onConnect:" + msg.toString());

		storage.addClientSession(user, connection);

		ClientSession client = storage.clients.get(user);

		DispatcherThread dt = new DispatcherThread(storage, client, messagesQueue);
		threads.put(user, dt);
		dt.start();
	}

	// called by dispatch upon receiving a disconnect message
	public void onDisconnect(DisconnectMsg msg) {

		String user = msg.getUser();

		Logger.log("onDisconnect:" + msg.toString());

		storage.removeClientSession(user);

		DispatcherThread dt = threads.get(user);

		System.out.println(msg.getUser() + " disconnetcing");
		if (dt != null) {
			dt.doStop();

			try {
				dt.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			threads.remove(user);
		}
	}

	@Override
	public void doStop() {
		super.doStop();

		threads.forEach((k, v) -> {
			System.out.println("stopping " + v.getName());
			v.doStop();
		});

		threads.forEach((k, v) -> {
			try {
				v.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
	}
}
