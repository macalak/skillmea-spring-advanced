package ite.librarymaster.domain.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Book Repository interface defines operations to
 * access and manipulate Books. It uses the Spring Data
 * feature by extension from the JpaRepository interface.
 * The JpaRepository supports pagination and sorting.
 *
 * The Spring's REST Repository feature can be used to directly expose
 * a Repository as Hypermedia REST API.
 *  - http://projects.spring.io/spring-data-rest/
 *  - https://en.wikipedia.org/wiki/HATEOAS
 *
 *
 * @author macalak@itexperts.sk
 *
 */

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
	
	Book findByIsbn(String isbn);

}
