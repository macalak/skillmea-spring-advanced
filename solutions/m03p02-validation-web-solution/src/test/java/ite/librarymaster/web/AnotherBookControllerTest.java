package ite.librarymaster.web;


import ite.librarymaster.model.Book;
import ite.librarymaster.model.BookGenre;
import ite.librarymaster.model.MediumAvailability;
import ite.librarymaster.service.LibraryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class AnotherBookControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    LibraryService libraryService;
    
    @Test
    public void testAllBook() throws Exception{
        given(libraryService.getAllBooks()).willAnswer(invocation -> Arrays.asList(new Book(1L,"LM-000001","A Game of Thrones","HarperCollins Publishers","George R. R. Martin","9780006479888", BookGenre.Fantasy, MediumAvailability.Available),
                new Book(2L,"LM-000002","The Hobbit","Random House Inc","J.R.R. Tolkien","9780345538376", BookGenre.Fantasy, MediumAvailability.Available),
                new Book(3L,"LM-000003","The Girl with the Dragon Tattoo","Quercus Publishing Plc","Stieg Larsson","9781849162883", BookGenre.Crime, MediumAvailability.Available),
                new Book(4L,"LM-000004","I, Robot","Random House Inc","Isaac Asimov","9780553382563", BookGenre.Scifi, MediumAvailability.Available)
        ));
        this.mockMvc.perform(get("/book/all").accept(MediaType.TEXT_HTML))
        .andExpect(status().isOk()).
        andExpect(view().name("/book/books")).
        andExpect(model().attribute("books", hasSize(4))).
        andExpect(model().attribute("books", hasItem(
                allOf(
                        hasProperty("id", is(1L)),
                        hasProperty("title", is("A Game of Thrones")),
                        hasProperty("author", is("George R. R. Martin")),
                        hasProperty("publisher", is("HarperCollins Publishers")),
                        hasProperty("isbn", is("9780006479888"))
                )
        )));
    }
}
