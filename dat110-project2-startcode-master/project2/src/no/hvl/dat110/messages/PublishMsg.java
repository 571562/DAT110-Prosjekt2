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
    private String message;
    private String topic;


    /**
     * Instantiates a new Message.
     *
     * @param user    the user
     * @param message the message
     * @param topic   the topic
     */
    public PublishMsg(String user, String message, String topic) {
        super(MessageType.PUBLISH, user);
        this.message = message;
        this.topic = topic;
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

    /**
     * Gets topic.
     *
     * @return the topic
     */
    public String getTopic() {
        return topic;
    }

    /**
     * Sets topic.
     *
     * @param topic the topic
     */
    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public String toString() {
        return "PublishMsg{" +
                "message='" + message + '\'' +
                ", topic='" + topic + '\'' +
                '}';
    }
}
