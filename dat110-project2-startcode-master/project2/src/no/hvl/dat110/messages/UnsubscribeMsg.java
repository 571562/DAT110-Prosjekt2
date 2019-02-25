package no.hvl.dat110.messages;

/**
 * The type Unsubscribe msg.
 */
public class UnsubscribeMsg extends Message {

	// TODO: 
	// Implement objectvariables, constructor, get/set-methods, and toString method

    /**
     * The Topic.
     */
    String topic;

    /**
     * Instantiates a new Message.
     *
     * @param topic the message
     */
    public UnsubscribeMsg(String topic) {
        this.topic = topic;
    }

    /**
     * Instantiates a new Message.
     *
     * @param type  the type
     * @param user  the user
     * @param topic the message
     */
    public UnsubscribeMsg(MessageType type, String user, String topic) {
        super(type, user);
        this.topic = topic;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getTopic() {
        return topic;
    }

    /**
     * Sets message.
     *
     * @param topic the message
     */
    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public String toString() {
        return "UnsubscribeMsg{" +
                "message='" + topic + '\'' +
                '}';
    }
}
