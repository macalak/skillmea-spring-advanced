package ite.librarymaster.service;

import ite.librarymaster.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Simple tester to verify the LibraryService component.
 */
@SpringBootTest
public class LibraryServiceImplTest {

    @Autowired
    private LibraryService libraryService;

    @Test
    public void testGetAllBooks() {
        List<Book> allBooks = libraryService.getAllBooks();
        assertNotNull(allBooks);
        assertEquals(4, allBooks.size());
    }

    @Test
    public void testGetBook() {
        Book book = libraryService.getBook(1L);
        assertNotNull(book);
    }

    @Test
    public void testGetBookNotFound() {
        assertThrows(RuntimeException.class, () -> {
            libraryService.getBook(100L);});
    }

}
