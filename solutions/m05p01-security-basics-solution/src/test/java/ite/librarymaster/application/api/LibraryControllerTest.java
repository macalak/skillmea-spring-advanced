package ite.librarymaster.application.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LibraryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void getAllBooks200() throws Exception {
        mockMvc.perform(get("/library/books")).andExpect(status().isOk());
    }
    @Test
    @WithMockUser(username = "admin", roles = {"USER"})
    public void getAllBooks403() throws Exception {
        mockMvc.perform(get("/library/books")).andExpect(status().isForbidden());
    }
    @Test
    @WithAnonymousUser
    public void getAllBooksAnonymous401() throws Exception {
        mockMvc.perform(get("/library/books")).andExpect(status().isUnauthorized());
    }
}