package ite.transport.application.service;

import ite.transport.application.domain.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

@Component
public class BookEventReceiver {
    final private static Logger LOG = LoggerFactory.getLogger(BookEventReceiver.class);


    @ServiceActivator(inputChannel = "bookInboundChannel")
    public void receiveMessage(Book book) {
        LOG.info("Message received {}", book);
    }
}
