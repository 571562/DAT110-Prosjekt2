package no.hvl.dat110.messages;

/*
        @author Herborg Irgens Sjo
 */

/**
 * The type Delete message msg.
 */
public class DeleteTopicMsg extends Message {
	
	// TODO: 
	// Implement objectvariables, constructor, get/set-methods, and toString method

    /**
     * The Topic.
     */
    private String topic;


    /**
     * Instantiates a new Delete message msg.
     *
     * @param type  the type
     * @param user  the user
     * @param topic the message
     */
    public DeleteTopicMsg(MessageType type, String user, String topic) {
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
        return "DeleteTopicMsg{" +
                "message='" + topic + '\'' +
                '}';
    }
}
