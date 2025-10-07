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
	public List<BookDTO> getAllBooks() {
        return  StreamSupport.stream(bookRepository.findAll().spliterator(),false)
                .map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
	}

    @Override
    @Transactional(readOnly=true)
    // TODO: Add caching here
    public BookDTO getBook(Long id) throws ItemNotFoundException {
        LOG.info("Getting Book by ID: {}", id);
        Book book = bookRepository.findById(id).orElse(null);
        if(book == null){
            throw new ItemNotFoundException("Book with id="+id+" not found.");
        }
        return modelMapper.map(book, BookDTO.class);
    }

    @Override
    //TODO: Put newly created Book into cache
    public BookDTO saveBook(BookDTO book) {
        Book savedBook = bookRepository.save(modelMapper.map(book, Book.class));
        LOG.info("Saved book: {}", savedBook);
        return modelMapper.map(savedBook, BookDTO.class);
    }

    // TODO: Add deleteBook() method implementation and evict deleted record from cache
}
