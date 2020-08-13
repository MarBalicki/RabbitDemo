package sda.java.rabbitdemo.data;

import org.springframework.data.annotation.Id;

public class MessageDB {

    @Id
    private String id;
    private String text;
    private String routingKey;

    public MessageDB() {
    }

    public MessageDB(String id, String text, String routingKey) {
        this.id = id;
        this.text = text;
        this.routingKey = routingKey;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }
}
