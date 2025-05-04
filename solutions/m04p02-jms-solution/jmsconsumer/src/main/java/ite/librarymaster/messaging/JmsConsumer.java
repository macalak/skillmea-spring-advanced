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

    @JmsListener(destination = "${jms.queue.destination}", selector = "type = 'String'")
    @Transactional
    public void receiveMessage(String message) {
        LOG.info("Consumed String message: {}", message);
        throw new RuntimeException();
    }

    @JmsListener(destination = "${jms.queue.destination}",selector = "type = 'Book'")
    @Transactional
    public void receiveBook(Book book) {
        LOG.info("Consumed Book: {}", book.toString());
    }
}
