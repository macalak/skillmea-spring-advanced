package ite.librarymaster.middleware;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class EhCacheEventLogger implements CacheEventListener<Object, Object> {
    private static final Logger LOG = LoggerFactory.getLogger(EhCacheEventLogger.class);

    @Override
    public void onEvent(CacheEvent<? extends Object, ? extends Object> event) {
        LOG.info("CACHE AUDIT: Event from cache received with key {}", event.getKey());
        LOG.info("CACHE AUDIT: Event Type is {}", event.getType());
        LOG.info("CACHE AUDIT: Event Value is {}", event.getNewValue().toString());
    }
}
