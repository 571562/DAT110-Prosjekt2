package no.hvl.dat110.messages;

/**
 * The type Connect msg.
 */
public class ConnectMsg extends Message {

    /**
     * Instantiates a new Connect msg.
     *
     * @param user the user
     */
    public ConnectMsg (String user) {

	    super(MessageType.CONNECT, user);
	}
	
}
