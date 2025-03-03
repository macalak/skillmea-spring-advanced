package ite.librarymaster.event;

import org.springframework.context.ApplicationEvent;

public class LibraryApplicationEvent  extends ApplicationEvent {
    public LibraryApplicationEvent(Object source) {
        super(source);
    }
}
