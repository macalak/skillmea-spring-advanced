package ite.librarymaster.service;

import java.time.Duration;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class LibraryChecker {
    private static final Logger LOG = LoggerFactory.getLogger(LibraryChecker.class);

    private final LibraryService  libraryService;
    private final TaskScheduler  threadPoolTaskScheduler;
    private final ThreadPoolTaskExecutor  threadPoolTaskExecutor;

//    public LibraryChecker(LibraryService libraryService, TaskScheduler threadPoolTaskScheduler) {
//        this.libraryService = libraryService;
//        this.threadPoolTaskScheduler = threadPoolTaskScheduler;
//    }


    public LibraryChecker(LibraryService libraryService, TaskScheduler threadPoolTaskScheduler, ThreadPoolTaskExecutor threadPoolTaskExecutor) {
        this.libraryService = libraryService;
        this.threadPoolTaskScheduler = threadPoolTaskScheduler;
        this.threadPoolTaskExecutor = threadPoolTaskExecutor;
    }

    @Scheduled(initialDelay = 2000, fixedRate = 5000 )
    @Async
    public void check(){
        LOG.info("--- [check] Library contains {} books ---", libraryService.getAllBooks().size());
    }

    @Scheduled(  fixedDelayString = "${checker.delay.in.milliseconds}")
    public void checkNoHardcodedDelay(){
        LOG.info("---[checkNoHardcodedDelay] Library contains {} books ---", libraryService.getAllBooks().size());
    }

    public void checkWithScheduler(long rate){
        LOG.info("Scheduling task using task scheduler...");
        threadPoolTaskScheduler.scheduleAtFixedRate(new LibraryTask("checkWithScheduler"), Duration.ofMillis(rate));
    }

    public void checkWithSchedulerOnceAfter(int delayInSeconds){
        LOG.info("Scheduling one shot task using task scheduler...");
        Calendar nextExecution = Calendar.getInstance();
        nextExecution.add(Calendar.SECOND, delayInSeconds);
        threadPoolTaskScheduler.schedule(new LibraryTask("checkWithOneShotScheduler"), nextExecution.toInstant());
    }

    public void checkWithSchedulerTrigger(Trigger trigger){
        LOG.info("Scheduling task using triggered task scheduler...");
        threadPoolTaskScheduler.schedule(new LibraryTask("checkWithSchedulerTrigger"), trigger);
    }

    class LibraryTask implements Runnable{
        private String name;

        public LibraryTask(String name){
            this.name = name;
        }

        @Override
        public void run() {
            LOG.info("---[{}}] Library contains {} books ---", name, libraryService.getAllBooks().size());
        }
    }
}
