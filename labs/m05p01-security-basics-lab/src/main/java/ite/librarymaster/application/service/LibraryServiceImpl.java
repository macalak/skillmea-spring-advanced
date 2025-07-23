package ite.librarymaster.application.service;

import ite.librarymaster.application.dto.BookDTO;
import ite.librarymaster.application.exception.ItemNotFoundException;
import ite.librarymaster.domain.model.Book;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import ite.librarymaster.domain.model.BookRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibraryServiceImpl implements LibraryService {
	static final Logger LOG = LoggerFactory.getLogger(LibraryServiceImpl.class);

	@Autowired
	private BookRepository bookRepository;
    @Autowired
    private ModelMapper modelMapepr;


    @Override
    // TODO 5: Protect method for unauthorized access
	public List<BookDTO> getAllBooks() {
        LOG.info("Executing method requires authorization ...");
        return bookRepository.findAll().stream()
                .map(book -> modelMapepr.map(book, BookDTO.class))
                .collect(Collectors.toList());
	}

    @Override
    // TODO 5: Protect method for unauthorized access
    public BookDTO getBookById(Long id) throws ItemNotFoundException {
        LOG.info("Executing method requires authorization ...");
        Optional<Book> book = bookRepository.findById(id);
        if(book.isPresent()){
            return modelMapepr.map(book.get(), BookDTO.class);
        }else {
            throw new ItemNotFoundException("Book with id=" + id + " not found.");
        }
    }
}
