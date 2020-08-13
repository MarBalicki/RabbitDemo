package sda.java.rabbitdemo.mvc;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sda.java.rabbitdemo.config.RabbitConfiguration;
import sda.java.rabbitdemo.data.MessageDB;
import sda.java.rabbitdemo.model.Message;
import sda.java.rabbitdemo.repository.MessageDBRepository;

import java.util.List;

@Controller
@RequestMapping("/mvc/message")
public class MessageController {

    private final RabbitTemplate rabbitTemplate;

    public MessageController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping
    public String greeting(Model model) {
        model.addAttribute("message", new Message());
        return "message";
    }

    @PostMapping
    public String sendMessage(@ModelAttribute Message message, Model model) {
        model.addAttribute("message", message);
        rabbitTemplate.convertAndSend(RabbitConfiguration.topicExchangeName);
        message.getRoutingKey();
        message.getText();
        return "result";
    }

}
