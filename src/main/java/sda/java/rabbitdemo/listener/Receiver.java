package sda.java.rabbitdemo.listener;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

//    private CountDownLatch latch = new CountDownLatch(1);

    private static final Logger log = LoggerFactory.getLogger(Receiver.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");


    public void receiveMessage(String message) {
        log.info("Received <" + message + ">. Received time: " + dateFormat.format(new Date()));
//        latch.countDown();
    }

//    public CountDownLatch getLatch() {
//        return latch;
//    }
}
