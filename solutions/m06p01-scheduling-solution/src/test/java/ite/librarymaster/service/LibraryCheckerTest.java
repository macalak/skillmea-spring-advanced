package ite.librarymaster.service;

import org.awaitility.Durations;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class LibraryCheckerTest {

    @SpyBean
    @Autowired
    LibraryChecker libraryChecker;

    @Test
    public void checkTest(){
        await().atMost(Durations.TEN_SECONDS).untilAsserted(() -> {
            verify(libraryChecker, atLeast(2)).check();
        });
    }

    @Test
    public void checkNoHardcodedDelayTest(){
        await().atMost(Durations.TEN_SECONDS).untilAsserted(() -> {
            verify(libraryChecker, atLeast(4)).checkNoHardcodedDelay();
        });
    }
}
