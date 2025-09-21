package ite.librarymaster.scheduling;

import ite.librarymaster.service.LibraryService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

@Component
public class AdaptiveScheduler {
    private final Logger LOG = LoggerFactory.getLogger(AdaptiveScheduler.class);
    private final ThreadPoolTaskScheduler taskScheduler;
    private final AdaptiveTrigger adaptiveTrigger;
    private final LibraryService libraryService;

    public AdaptiveScheduler(ThreadPoolTaskScheduler taskScheduler, AdaptiveTrigger adaptiveTrigger, LibraryService libraryService) {
        this.taskScheduler = taskScheduler;
        this.adaptiveTrigger = adaptiveTrigger;
        this.libraryService = libraryService;
    }

    @PostConstruct
    public void scheduleTask() {
        LOG.info("Schedule task with Adaptive Trigger ...");
        Runnable task = () -> LOG.info("---[AdaptiveScheduler] Library contains {} books ---",  libraryService.getAllBooks().size());
        taskScheduler.schedule(task, adaptiveTrigger);
    }
}
