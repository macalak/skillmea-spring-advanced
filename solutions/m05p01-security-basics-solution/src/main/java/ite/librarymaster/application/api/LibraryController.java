package ite.librarymaster.application.api;

import ite.librarymaster.application.dto.BookDTO;
import ite.librarymaster.application.exception.ItemNotFoundException;
import ite.librarymaster.application.service.LibraryService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Library REST Controller.
 * 
 * @author macalak@itexperts.sk
 *
 */
@RestController
@RequestMapping("/library")
public class LibraryController {
	final private static Logger LOG = LoggerFactory.getLogger(LibraryController.class);
    
    @Autowired
    LibraryService libraryService;
    
    @RequestMapping(produces={"application/json"}, value="/books", method=RequestMethod.GET )
    @Secured("ROLE_ADMIN")
    public List<BookDTO> getAllBooks() {
    	LOG.info("Getting all books...");
        return libraryService.getAllBooks();
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @RequestMapping(produces={"application/json"}, value="/books/{id}", method=RequestMethod.GET )
    public BookDTO getBookById(@PathVariable("id") Long id) throws ItemNotFoundException{
    	LOG.info("Getting book identified by id:{} ...", id);
        return libraryService.getBookById(id);
    }
   
}
