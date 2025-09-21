package ite.librarymaster.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.Calendar;

@Component
public class LibraryChecker {
    private static final Logger LOG = LoggerFactory.getLogger(LibraryChecker.class);

    // TODO: check() with annotation
    // TODO: Try Async

    // TODO: checkWithSchedulerOnceAfter()

    // TODO: LibraryTask
}
