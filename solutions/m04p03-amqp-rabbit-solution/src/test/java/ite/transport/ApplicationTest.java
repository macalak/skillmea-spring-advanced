package ite.transport;

import ite.transport.application.domain.model.Book;
import ite.transport.application.service.EventPublisherGateway;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutionException;

@SpringBootTest
public class ApplicationTest {
    private static final Logger LOG= LoggerFactory.getLogger(ApplicationTest.class);

    @Autowired
    EventPublisherGateway eventPublisherGateway;

    @Test
    public void amqpPublishMessageTest() throws ExecutionException, InterruptedException {
        eventPublisherGateway.send(new Book(5L,"New book","Author","Publisher","Available","LM-00005","Drama","1234567891234"));
        Thread.sleep(10000);
    }

}
