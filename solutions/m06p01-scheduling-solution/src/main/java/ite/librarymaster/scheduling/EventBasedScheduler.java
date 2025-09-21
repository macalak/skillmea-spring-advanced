package ite.librarymaster.scheduling;

import ite.librarymaster.event.BookCreatedEvent;
import ite.librarymaster.service.LibraryChecker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EventBasedScheduler {
    private final Logger LOG = LoggerFactory.getLogger(EventBasedScheduler.class);

    private final LibraryChecker libraryChecker;

    public EventBasedScheduler(LibraryChecker libraryChecker) {
        this.libraryChecker = libraryChecker;
    }

    @EventListener(BookCreatedEvent.class)
    public void schedule(BookCreatedEvent event){
        LOG.info("Book {} created.", event);
        libraryChecker.checkWithSchedulerOnceAfter(10);
        LOG.info("Task LibraryChecker scheduled.");
    }

}
