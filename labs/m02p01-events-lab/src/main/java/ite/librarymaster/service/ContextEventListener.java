package ite.librarymaster.service;


import ite.librarymaster.dao.BookRepository;
import ite.librarymaster.model.Book;
import ite.librarymaster.model.BookGenre;
import ite.librarymaster.model.MediumAvailability;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ContextEventListener {
   private final static Logger LOG = LoggerFactory.getLogger(ContextEventListener.class);

   @Autowired
   BookRepository bookRepository;

   @EventListener
   public void handleContextRefreshed(ContextRefreshedEvent cse) {
      LOG.info("SPRING BUILD-IN EVENT: Context REFRESHED.");
      bookRepository.save(new Book(
              null,
              "Test",
              "Test Title",
              "Test Publisher",
              "Test Author",
              "9780000000000",
              BookGenre.Fantasy,
              MediumAvailability.Available));
   }

   @EventListener
   public void handleApplicationStart(ApplicationStartedEvent cse) {
      LOG.info("SPRING BUILD-IN EVENT: Boot App context started.");
      LOG.info("This instance registered into service registry.");
   }
}
