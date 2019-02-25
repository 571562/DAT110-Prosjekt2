package no.hvl.dat110.messages;

/**
 * The type Message.
 */
public abstract class Message {

	private MessageType type;
	private String user;

    /**
     * Instantiates a new Message.
     */
    public Message() {
		
	}

    /**
     * Instantiates a new Message.
     *
     * @param type the type
     * @param user the user
     */
    public Message(MessageType type, String user) {
		this.type = type;
		this.user = user;
	}

    /**
     * Gets type.
     *
     * @return the type
     */
    public MessageType getType() {
	    return this.type; }


    /**
     * Gets user.
     *
     * @return the user
     */
    public String getUser() {

	    return user;
	}

	@Override
	public String toString() {

	    return "Message [type=" + type + ", user=" + user + "]";
	}
	
	
}
