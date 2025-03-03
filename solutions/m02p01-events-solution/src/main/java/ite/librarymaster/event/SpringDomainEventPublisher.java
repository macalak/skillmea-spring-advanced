package ite.librarymaster.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class SpringDomainEventPublisher implements DomainEventPublisher {
    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void fireEvent(GenericEvent genericEvent) {
        applicationEventPublisher.publishEvent(genericEvent.getPayload());
    }
}
