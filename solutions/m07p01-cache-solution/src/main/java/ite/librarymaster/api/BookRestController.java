package ite.librarymaster.api;

import ite.librarymaster.service.ItemNotFoundException;
import ite.librarymaster.service.LibraryService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * The Spring  @RestController controller handling API requests.
 */
@RestController
@RequestMapping("/api-book")
public class BookRestController {
    final Logger logger = LoggerFactory.getLogger(BookRestController.class);

	private final LibraryService libraryService;

	public BookRestController(LibraryService libraryService) {
		this.libraryService = libraryService;
	}

	@GetMapping(value = "/books", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public List<BookDTO> allBook() {
	    logger.info("Retrieving all Books...");
		return libraryService.getAllBooks();
	}

	@GetMapping(value="/books/{id}",  produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public BookDTO book(@PathVariable("id") Long id) throws ItemNotFoundException{
	    logger.info("Retrieving Book with ID:{}...",id);
		return libraryService.getBook(id);
	}


	 @PostMapping(value = "/books", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	 public ResponseEntity processCreation(@RequestBody @Valid BookDTO book) {
		logger.info("Adding Book with ISBN: {}...",book.isbn);
		libraryService.saveBook(book);
		return new ResponseEntity(HttpStatus.CREATED);
    }


}
