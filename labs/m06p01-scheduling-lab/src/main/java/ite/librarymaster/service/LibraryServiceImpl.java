package ite.librarymaster.service;

import ite.librarymaster.dao.BookRepository;
import ite.librarymaster.event.BookCreatedEvent;
import ite.librarymaster.model.Book;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LibraryServiceImpl implements LibraryService{
    private static final Logger LOG = LoggerFactory.getLogger(LibraryServiceImpl.class);

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    public LibraryServiceImpl(BookRepository bookRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    @Override
	@Transactional(readOnly=true)
	public List<Book> getAllBooks() {
        return (List<Book>) bookRepository.findAll();
	}

    @Override
    @Transactional(readOnly=true)
    public Book getBook(Long id) throws ItemNotFoundException {
        Book book = bookRepository.findById(id).orElse(null);
        if(book == null){
            throw new ItemNotFoundException("Book with id="+id+" not found.");
        }
        return book;
    }

    @Override
    public Book saveBook(Book book) {
        bookRepository.save(book);
        // TODO: Publish event
        return book;
    }
}
