package ite.librarymaster.web;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    public void testAllBook() throws Exception{
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
    
    
    @Test
    public void testBookDetail() throws Exception{
        this.mockMvc.perform(get("/book/1").accept(MediaType.TEXT_HTML))
        .andExpect(status().isOk())
        .andExpect(view().name("/book/book"))
        .andExpect(model().attribute("book", hasProperty("id", is(1L))))
        .andExpect(model().attribute("book", hasProperty("title", is("A Game of Thrones"))))
        .andExpect(model().attribute("book", hasProperty("author", is("George R. R. Martin"))))
        .andExpect(model().attribute("book", hasProperty("publisher", is("HarperCollins Publishers"))))
        .andExpect(model().attribute("book", hasProperty("isbn", is("9780006479888"))));
    }
    
    @Test
    public void testNonExistentBookDetail() throws Exception{
        this.mockMvc.perform(get("/book/99").accept(MediaType.TEXT_HTML))
        .andExpect(status().isNotFound())
        .andExpect(view().name("error/404"));
    }
    
}
