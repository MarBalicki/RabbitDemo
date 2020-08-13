package sda.java.rabbitdemo.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import sda.java.rabbitdemo.MongoRunner;
import sda.java.rabbitdemo.config.RabbitConfiguration;
import sda.java.rabbitdemo.data.MessageDB;
import sda.java.rabbitdemo.repository.MessageDBRepository;

@Component
public class AllMessagesListener {

    private static final Logger log = LoggerFactory.getLogger(MongoRunner.class);

    private final MessageDBRepository messageDBRepository;

    public AllMessagesListener(MessageDBRepository messageDBRepository) {
        this.messageDBRepository = messageDBRepository;
    }


    @RabbitListener(queues = RabbitConfiguration.queueAllMessages)
    public void handleMessage(String text, Message message) {
        String routingKey = (String) message.getHeaders().get("amqp_receivedRoutingKey");
        log.info("Message: " + text + " routing key: " + routingKey);

        MessageDB messageDB = new MessageDB();
        messageDB.setText(text);
        messageDB.setRoutingKey(routingKey);
        messageDBRepository.save(messageDB);
        log.info("Message saved!");

    }
}
