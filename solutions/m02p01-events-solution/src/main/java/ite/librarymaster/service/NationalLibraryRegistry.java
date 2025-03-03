package ite.librarymaster.service;

import ite.librarymaster.event.BookCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class NationalLibraryRegistry {
    private final Logger LOG = LoggerFactory.getLogger(NationalLibraryRegistry.class);

    @EventListener(BookCreatedEvent.class)
    public void register(BookCreatedEvent event){
        LOG.info("Book {} registered into national registry.", event);
    }
}
