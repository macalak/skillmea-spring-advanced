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

    // TODO: Add and try transactional behaviour: @Transactional
    public void sendStringMessage(String message) {
        // TODO: Add implementation
    }
    public void sendBook(Book book) {
        // TODO: Aad implementation
    }
}
