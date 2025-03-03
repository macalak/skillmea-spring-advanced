package ite.librarymaster.service;

import ite.librarymaster.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class LibraryServiceImplTest {

    @Autowired
    private LibraryService libraryService;

    @Test
    public void testGetAllBooks() {
        List<Book> allBooks = libraryService.getAllBooks();
        assertNotNull(allBooks);
        // Verify number of books returned
        assertEquals(4, allBooks.size());
    }

    @Test
    public void testGetBook() {
        Book book = libraryService.getBook(1L);
        assertNotNull(book);
    }

}
