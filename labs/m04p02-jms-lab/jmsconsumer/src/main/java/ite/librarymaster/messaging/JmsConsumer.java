package ite.librarymaster.messaging;

import ite.librarymaster.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class JmsConsumer {
    private static final Logger LOG = LoggerFactory.getLogger(JmsConsumer.class);

    public void receiveMessage(String message) {
        // TODO: Add implementation
    }

    public void receiveBook(Book book) {
        // TODO: Add implementation
    }
}
