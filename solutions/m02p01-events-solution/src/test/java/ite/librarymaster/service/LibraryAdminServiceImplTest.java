package ite.librarymaster.service;

import ite.librarymaster.event.BookCreatedEvent;
import ite.librarymaster.integration.EuropeanLibraryRegistryClient;
import ite.librarymaster.model.Book;
import ite.librarymaster.model.BookGenre;
import ite.librarymaster.model.MediumAvailability;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class LibraryAdminServiceImplTest {

    @Autowired
    private LibraryAdminService libraryAdminService;
    @Autowired
    private LibraryService libraryService;
    @MockBean
    EuropeanLibraryRegistryClient europeanLibraryRegistryClient;

    @Test
    public void testCreateBook() {
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

        ArgumentCaptor<BookCreatedEvent> argumentCaptor = ArgumentCaptor.forClass(BookCreatedEvent.class);
        verify(europeanLibraryRegistryClient).registerBookToRemoteRegistry(argumentCaptor.capture());
        assertEquals("9780006479888", argumentCaptor.getValue().isbn);
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
        try {
            libraryAdminService.deleteBook(book);
        } catch (LibraryException e) {
            // swallow exception
        }
        // If business transaction is rolled back, book should not be deleted
        assertNotNull(libraryService.getBook(book.getId()));
    }
}
