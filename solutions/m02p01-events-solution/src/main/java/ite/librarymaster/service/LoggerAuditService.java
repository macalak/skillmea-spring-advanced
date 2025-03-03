package ite.librarymaster.service;

import ite.librarymaster.event.BookDeletedEvent;
import ite.librarymaster.event.GenericEvent;
import ite.librarymaster.event.LibraryApplicationEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
public class LoggerAuditService {
    private final static Logger LOG = LoggerFactory.getLogger(LoggerAuditService.class);

    //@EventListener
    // Listener bound to Tx
    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    //@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    //@Async
    public void log(GenericEvent event) {
        LOG.info("AUDIT LOG: {}", event.getPayload());
    }

    @EventListener(condition = "#event.payload.catId == 'LM-000099'")
    public void handleFilteredEvent(GenericEvent event){
        LOG.info("AUDIT LOG FILTERED: {}", event.getPayload());

   }

    @EventListener(LibraryApplicationEvent.class)
    public void handleApplicationEvent(LibraryApplicationEvent event){
        LOG.info("AUDIT APPLICATION LOG: Book event {}", event.toString());
    }

}
