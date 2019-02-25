package no.hvl.dat110.messages;

/**
 * The type Subscribe msg.
 */
public class SubscribeMsg extends Message {

	// TODO: 
	// Implement objectvariables, constructor, get/set-methods, and toString method

    /**
     * The Topic.
     */
    private String topic;


    /**
     * Instantiates a new Message.
     *
     * @param type  the type
     * @param user  the user
     * @param topic the message
     */
    public SubscribeMsg(MessageType type, String user, String topic) {
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
        return "SubscribeMsg{" +
                "message='" + topic + '\'' +
                '}';
    }
}
