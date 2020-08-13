package sda.java.rabbitdemo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import sda.java.rabbitdemo.data.MessageDB;

import java.util.List;

public interface MessageDBRepository extends MongoRepository<MessageDB, String> {

//    public MessageDB findByText(String text);
//    public List<MessageDB> findByRoutingKey(String routingKey);
}
