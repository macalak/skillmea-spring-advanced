package ite.librarymaster.web;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class BookRestControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    public void testGetBooks() throws Exception{
        this.mockMvc.perform(get("/api-book/books")
        .accept(MediaType.APPLICATION_JSON_VALUE))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$[*].catId").isNotEmpty());
    }
    
    
    @Test
    public void testGetBook() throws Exception{
        this.mockMvc.perform(get("/api-book/books/1")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.catId").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.catId").value("LM-000001"));
    }

    @Test
    public void testGetNotExistentBook() throws Exception{
        this.mockMvc.perform(get("/api-book/books/100")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
    
    @Test
    public void testCreateBook() throws Exception{
        String body = "{\"catId\":\"LM-000099\",\"title\":\"A Game of Thrones\",\"availability\":\"Available\",\"publisher\":\"HarperCollins Publishers\",\"author\":\"George R. R. Martin\",\"isbn\":\"9780006479888\",\"genre\":\"Fantasy\"}";
        this.mockMvc.perform(post("/api-book/books").contentType(MediaType.APPLICATION_JSON_VALUE).content(body))
        .andExpect(status().isCreated());
    }

    @Test
    public void testBookNotCreatedWithEmptyAuthor() throws Exception{
        String body = "{\"catId\":\"LM-000099\",\"title\":\"A Game of Thrones\",\"availability\":\"Available\",\"publisher\":\"HarperCollins Publishers\",\"isbn\":\"9780006479888\",\"genre\":\"Fantasy\"}";
        this.mockMvc.perform(post("/api-book/books").contentType(MediaType.APPLICATION_JSON_VALUE).content(body))
        .andExpect(status().is4xxClientError());
    }

    @Test
    public void testBookNotCreatedWithInvalidCatId() throws Exception{
        String body = "{\"catId\":\"-000099\",\"title\":\"A Game of Thrones\",\"availability\":\"Available\",\"publisher\":\"HarperCollins Publishers\",\"author\":\"George R. R. Martin\",\"isbn\":\"9780006479888\",\"genre\":\"Fantasy\"}";
        this.mockMvc.perform(post("/api-book/books").contentType(MediaType.APPLICATION_JSON_VALUE).content(body))
        .andExpect(status().is4xxClientError());
    }
    
}
