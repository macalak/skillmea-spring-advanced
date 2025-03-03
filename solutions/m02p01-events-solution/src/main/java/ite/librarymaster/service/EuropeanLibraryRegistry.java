package ite.librarymaster.service;

import ite.librarymaster.event.BookCreatedEvent;
import ite.librarymaster.event.BookDeletedEvent;
import ite.librarymaster.integration.EuropeanLibraryRegistryClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
public class EuropeanLibraryRegistry {
    private final Logger LOG = LoggerFactory.getLogger(EuropeanLibraryRegistry.class);

    private final EuropeanLibraryRegistryClient libraryRegistryClient;

    public EuropeanLibraryRegistry(EuropeanLibraryRegistryClient libraryRegistryClient) {
        this.libraryRegistryClient = libraryRegistryClient;
    }

    @EventListener
    public void register(BookCreatedEvent event){
        LOG.info("Book {} registered into european registry.", event.title);
        libraryRegistryClient.registerBookToRemoteRegistry(event);
    }

    @EventListener(condition = "#event.genre.toString == 'Fantasy'")
    public void handleOnlyFantasy(BookCreatedEvent event){
        LOG.info("Handling only Fantasy {}", event);
    }

    //@EventListener
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void unregister(BookDeletedEvent event){
        LOG.info("Book with ID:{} unregistered from european registry.", event.id);
    }

}
