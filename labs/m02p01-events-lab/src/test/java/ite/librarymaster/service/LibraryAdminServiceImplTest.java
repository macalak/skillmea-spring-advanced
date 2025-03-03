package ite.librarymaster.service;

import ite.librarymaster.model.Book;
import ite.librarymaster.model.BookGenre;
import ite.librarymaster.model.MediumAvailability;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class LibraryAdminServiceImplTest {

    @Autowired
    private LibraryAdminService libraryAdminService;

    @Test
    @DirtiesContext
    public void testCreateBook() {
        Book book = new Book(
                null,
                "LM-000099",
                "A Game of Thrones",
                "HarperCollins Publishers",
                "George R. R. Martin",
                "9780006479888",
                BookGenre.Scifi,
                MediumAvailability.Available);
        libraryAdminService.addBook(book);
    }

    @Test
    public void testDeleteBook() {
        Book book = new Book(
                null,
                "LM-000099",
                "A Game of Thrones",
                "HarperCollins Publishers",
                "George R. R. Martin",
                "9780006479888",
                BookGenre.Fantasy,
                MediumAvailability.Available);
        libraryAdminService.addBook(book);
        libraryAdminService.deleteBook(book);
    }
}
