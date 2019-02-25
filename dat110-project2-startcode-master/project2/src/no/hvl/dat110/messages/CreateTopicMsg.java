package no.hvl.dat110.messages;
/*
       @author Herborg Irgens Sjo
 */

public class CreateTopicMsg extends Message {
	
	// TODO: 
	// Implement objectvariables, constructor, get/set-methods, and toString method

    String topic;

    public CreateTopicMsg() {

    }

    public CreateTopicMsg(MessageType type, String user, String topic) {
        super(type, user);
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public String toString() {
        return "CreateTopicMsg{" +
                "topic='" + topic + '\'' +
                '}';
    }
}
