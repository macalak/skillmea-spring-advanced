package ite.transport.application.service;

import ite.transport.application.domain.model.Book;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface EventPublisherGateway {
    @Gateway(requestChannel = "bookOutboundChannel")
    void send(Book event);
}
