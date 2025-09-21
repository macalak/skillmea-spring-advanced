package ite.librarymaster.api;

import ite.librarymaster.model.Book;
import ite.librarymaster.service.ItemNotFoundException;
import ite.librarymaster.service.LibraryService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


/**
 * The Spring  @RestController controller handling API requests.
 */
@RestController
@RequestMapping("/api-book")
public class BookRestController {
    final Logger logger = LoggerFactory.getLogger(BookRestController.class);


	private final LibraryService libraryService;
	private final ModelMapper modelMapper;

	public BookRestController(LibraryService libraryService, ModelMapper modelMapper) {
		this.libraryService = libraryService;
		this.modelMapper = modelMapper;
	}

	@GetMapping(value = "/books", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public List<BookDTO> allBook() {
	    logger.info("Retrieving all Books...");
		return libraryService.getAllBooks().stream()
				.map(book -> modelMapper.map(book, BookDTO.class))
				.collect(Collectors.toList());
	}

	@GetMapping(value="/books/{id}",  produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public BookDTO book(@PathVariable Long id, Model model) throws ItemNotFoundException{
	    logger.info("Retrieving Book with ID:{}...",id);
		return modelMapper.map(libraryService.getBook(id), BookDTO.class);
	}


	 @PostMapping(value = "/books", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	 public ResponseEntity processCreation(@RequestBody /*@Validated*/ @Valid BookDTO book) {
		logger.info("Adding Book with ISBN: {}...",book.isbn);
		libraryService.saveBook(modelMapper.map(book, Book.class));
		return new ResponseEntity(HttpStatus.CREATED);
    }


}
