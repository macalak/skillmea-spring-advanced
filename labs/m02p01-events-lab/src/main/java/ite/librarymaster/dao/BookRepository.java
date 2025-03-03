package ite.librarymaster.dao;

import ite.librarymaster.model.Book;
import org.springframework.data.repository.CrudRepository;

/**
 * Book Repository interface defines operations to
 * access and manipulate Books.
 * 
 * @author macalak@itexperts.sk
 *
 */
public interface BookRepository extends CrudRepository<Book, Long> {

	Book findByIsbn(String isbn);
}
