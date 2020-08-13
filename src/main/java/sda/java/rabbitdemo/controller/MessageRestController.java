package sda.java.rabbitdemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import sda.java.rabbitdemo.data.MessageDB;
import sda.java.rabbitdemo.listener.Receiver;
import sda.java.rabbitdemo.config.RabbitConfiguration;
import sda.java.rabbitdemo.model.Message;
import sda.java.rabbitdemo.repository.MessageDBRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/message")
public class MessageRestController {

    private final RabbitTemplate rabbitTemplate;

    private static final Logger log = LoggerFactory.getLogger(Receiver.class);

    private final MessageDBRepository messageDBRepository;


    public MessageRestController(RabbitTemplate rabbitTemplate, MessageDBRepository messageDBRepository) {
        this.rabbitTemplate = rabbitTemplate;
        this.messageDBRepository = messageDBRepository;
    }

    @PostMapping
    public void sendMessage(@RequestBody @Valid Message message) {
        log.info("Message received: " + message.getText());
        rabbitTemplate.convertAndSend(RabbitConfiguration.topicExchangeName,
                message.getRoutingKey(),
                message.getText());
    }

    @GetMapping
    public List<MessageDB> findAllMessages(@RequestParam(value = "page",defaultValue = "0") int page,
                                           @RequestParam(value = "size", defaultValue = "100") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return messageDBRepository.findAll(pageRequest).getContent();
    }
}
