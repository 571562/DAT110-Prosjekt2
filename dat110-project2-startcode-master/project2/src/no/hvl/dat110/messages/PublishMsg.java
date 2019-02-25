package no.hvl.dat110.messages;

/**
 * The type Publish msg.
 */
public class PublishMsg extends Message {
	
	// TODO: 
	// Implement objectvariables, constructor, get/set-methods, and toString method

    /**
     * The Topic.
     */
    String message;

    /**
     * Instantiates a new Message.
     *
     * @param message the message
     */
    public PublishMsg(String message) {
        this.message = message;
    }

    /**
     * Instantiates a new Message.
     *
     * @param type  the type
     * @param user  the user
     * @param message the message
     */
    public PublishMsg(MessageType type, String user, String message) {
        super(type, user);
        this.message = message;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets message.
     *
     * @param message the message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "PublishMsg{" +
                "message='" + message + '\'' +
                '}';
    }
}
