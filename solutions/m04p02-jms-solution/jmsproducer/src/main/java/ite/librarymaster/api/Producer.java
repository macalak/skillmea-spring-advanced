package ite.librarymaster.api;

import ite.librarymaster.messaging.JmsProducer;
import ite.librarymaster.model.Book;
import ite.librarymaster.model.BookGenre;
import ite.librarymaster.model.MediumAvailability;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Producer {
    private final static Logger LOG = LoggerFactory.getLogger(Producer.class);
    private JmsProducer jmsProducer;

    public Producer(JmsProducer jmsProducer) {
        this.jmsProducer = jmsProducer;
    }

    @PostMapping(path = "/send-message", consumes = "text/plain")
    public void sendMessage(@RequestBody String payload){
        LOG.info("Sending payload {} to JMS destination ...", payload);
        try {
            jmsProducer.sendStringMessage(payload);
        }catch (RuntimeException e){

        }
    }

    @PostMapping(path = "/produce-book")
    public void produceBook(){
        Book book =  new Book(1L,"LM-000001","A Game of Thrones",MediumAvailability.Available,
                "George R. R. Martin","9780006479888", BookGenre.Fantasy, "HarperCollins Publishers");
        LOG.info("Producing book: {}...", book);
        jmsProducer.sendBook(book);
    }
}
