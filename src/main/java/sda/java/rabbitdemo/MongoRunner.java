package sda.java.rabbitdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sda.java.rabbitdemo.data.MessageDB;
import sda.java.rabbitdemo.repository.MessageDBRepository;

import java.util.List;

@SpringBootApplication
public class MongoRunner implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(MongoRunner.class);
    private final MessageDBRepository messageDBRepository;

    @Autowired
    public MongoRunner(MessageDBRepository messageDBRepository) {
        this.messageDBRepository = messageDBRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        messageDBRepository.deleteAll();

//        MessageDB messageDB1 = new MessageDB();
//        messageDB1.setText("Hello!");
//        messageDB1.setRoutingKey("key");
//
//        MessageDB messageDB2 = new MessageDB();
//        messageDB2.setText("Hello2!");
//        messageDB2.setRoutingKey("key2");
//
//        messageDBRepository.saveAll(List.of(messageDB1, messageDB2));
//
//        log.info("Message saved.");

        messageDBRepository.findAll()
                .forEach(MongoRunner::loggerMessage);

    }

    private static void loggerMessage(MessageDB messageDB) {
        log.info(messageDB.getId());
        log.info(messageDB.getText());
        log.info(messageDB.getRoutingKey());
    }
}
