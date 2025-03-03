package ite.librarymaster.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class LibraryApplicationEventListener implements ApplicationListener {
    private final static Logger LOG = LoggerFactory.getLogger(LibraryApplicationEventListener.class);

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        LOG.info("Application Event received: {}", event.toString());
    }
}
