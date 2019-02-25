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
    private String topic;


    /**
     * Instantiates a new Message.
     *
     * @param user  the user
     * @param topic the message
     */
    public UnsubscribeMsg(String user, String topic) {
        super(MessageType.UNSUBSCRIBE, user);
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
