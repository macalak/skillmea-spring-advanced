package ite.librarymaster.integration;

import ite.librarymaster.event.BookCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class EuropeanLibraryRegistryClientImpl implements EuropeanLibraryRegistryClient {
    private final Logger LOG = LoggerFactory.getLogger(EuropeanLibraryRegistryClientImpl.class);

    @Override
    public void registerBookToRemoteRegistry(BookCreatedEvent bookCreatedEvent) {
        LOG.info("Connecting to service and registering book {}", bookCreatedEvent);
    }
}
