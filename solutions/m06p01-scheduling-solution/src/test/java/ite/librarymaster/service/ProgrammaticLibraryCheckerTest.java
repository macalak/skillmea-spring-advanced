package ite.librarymaster.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.support.CronTrigger;

@SpringBootTest
public class ProgrammaticLibraryCheckerTest {

    @Autowired
    LibraryChecker libraryChecker;

    @Test
    public void checkTest() throws InterruptedException {
        libraryChecker.checkWithScheduler(5000);
        Thread.sleep(20000);
    }

    @Test
    public void checkTriggerTest() throws InterruptedException {
        CronTrigger cronTrigger = new CronTrigger("0/5 * * * * ?");
        libraryChecker.checkWithSchedulerTrigger(cronTrigger);
        Thread.sleep(20000);
    }
}
