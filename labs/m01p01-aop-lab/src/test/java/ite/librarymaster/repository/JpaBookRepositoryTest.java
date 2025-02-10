package ite.librarymaster.repository;

import ite.librarymaster.model.Book;
import ite.librarymaster.model.BookGenre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Simple tester to verify BookRepository component.
 */
@SpringBootTest
public class JpaBookRepositoryTest {

    @Autowired
    @Qualifier("jpaBookRepository")
    private BookRepository bookRepository;

    @Test
    public void testGetAllBooks() {
        List<Book> allBooks = bookRepository.findAll();
        assertNotNull(allBooks);
        // Verify number of books returned
        assertEquals(4, allBooks.size());
    }

    @Test
    public void testFindByIsbn() {
        Book books = bookRepository.findByIsbn("9780553382563");
        assertNotNull(books);
        assertEquals("I, Robot", books.getTitle());
        assertEquals("Random House Inc", books.getPublisher());
        assertEquals("Isaac Asimov", books.getAuthor());
        assertEquals(BookGenre.Scifi, books.getGenre());
    }

    @Test
    public void testFindById() {
        Book books = bookRepository.findById(2L);
        assertNotNull(books);
        assertEquals("The Hobbit", books.getTitle());
        assertEquals("Random House Inc", books.getPublisher());
        assertEquals("J.R.R. Tolkien", books.getAuthor());
        assertEquals(BookGenre.Fantasy, books.getGenre());
    }
}
