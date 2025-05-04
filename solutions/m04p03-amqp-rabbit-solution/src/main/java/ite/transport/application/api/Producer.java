package ite.transport.application.api;

import ite.transport.application.service.EventPublisherGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Producer {
    private final static Logger LOG = LoggerFactory.getLogger(Producer.class);
    @Autowired
    EventPublisherGateway eventPublisherGateway;

    @PostMapping(path = "/produce-book")
    public void produceBook(){
        eventPublisherGateway.send(new ite.transport.application.domain.model.Book(5L,"New book","Author","Publisher","Available","LM-00005","Drama","1234567891234"));
    }
}
