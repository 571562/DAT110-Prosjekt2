package no.hvl.dat110.messages;

/**
 * The type Disconnect msg.
 */
public class DisconnectMsg extends Message {

    /**
     * Instantiates a new Disconnect msg.
     *
     * @param user the user
     */
    public DisconnectMsg(String user) {
		super(MessageType.DISCONNECT, user);
	}
	
}
