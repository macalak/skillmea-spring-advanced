package ite.librarymaster.service;

import ite.librarymaster.api.BookDTO;
import ite.librarymaster.dao.BookRepository;
import ite.librarymaster.model.Book;


import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


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

    @Override
	@Transactional(readOnly=true)
    @CacheEvict(cacheNames = "books", allEntries = true)
	public List<BookDTO> getAllBooks() {
        return  StreamSupport.stream(bookRepository.findAll().spliterator(),false)
                .map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
	}

    @Override
    @Transactional(readOnly=true)
    // Cache uses a default key generation strategy that creates a SimpleKey that consists of all the parameters with which the method was called.
    // This requires the parameters to have a  hashCode()/equals() implementation.
    //@Cacheable(cacheNames = "books")
    // Explicit Key definition.
    @Cacheable(cacheNames = "books", key="#id")
    // Conditional caching.
    //@Cacheable(cacheNames = "books", condition="#id != null ? #id%2 == 0 : true")
    public BookDTO getBook(Long id) throws ItemNotFoundException {
        
        LOG.info("1: Getting Book by ID: {}", id);
        Book book = bookRepository.findById(id).orElse(null);
        if(book == null){
            throw new ItemNotFoundException("Book with id="+id+" not found.");
        }
        return modelMapper.map(book, BookDTO.class);
    }


    @Override
    // Put newly created Book into cache
    @CachePut( cacheNames = "books", key = "#result.id", condition = "#result.id != null" )
    @Transactional
    public BookDTO saveBook(BookDTO book) {
        Book savedBook = bookRepository.save(modelMapper.map(book, Book.class));
        LOG.info("Saved book: {}", savedBook);
        return modelMapper.map(savedBook, BookDTO.class);
    }

    @Override
    @CacheEvict(cacheNames = "books", key="#id")
    public void deleteBook(BookDTO book) {
        LOG.info("Deleted book: {}", book);
    }
}
