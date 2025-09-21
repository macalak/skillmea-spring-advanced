package ite.librarymaster.dao;

import ite.librarymaster.model.Book;
import org.springframework.data.repository.CrudRepository;

/**
 * Book Repository interface extends the Spring's Data Crud Repository.
 * 
 * @author macalak@itexperts.sk
 *
 */
public interface BookRepository extends CrudRepository<Book, Long> {
	
	Book findByIsbn(String isbn);

}
