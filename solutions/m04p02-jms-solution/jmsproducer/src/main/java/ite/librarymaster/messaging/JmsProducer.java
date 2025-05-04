package ite.librarymaster.messaging;

import ite.librarymaster.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class JmsProducer {
    private final static Logger LOG = LoggerFactory.getLogger(JmsProducer.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${jms.queue.destination}")
    private String queue;

    @Transactional
    public void sendStringMessage(String message) {
        LOG.info("Sending message {} ...", message);
        jmsTemplate.convertAndSend(queue, message, messagePostProcessor -> {
            messagePostProcessor.setStringProperty("type",
                    "String");
            return messagePostProcessor;
        });
        //throw new RuntimeException();
    }
    public void sendBook(Book book) {

        jmsTemplate.convertAndSend(queue, book, messagePostProcessor -> {
            messagePostProcessor.setStringProperty("type",
                    "Book");
            return messagePostProcessor;
        });
    }
}
