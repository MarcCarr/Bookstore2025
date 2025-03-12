package fi.haagahelia.BookstoreMC;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import fi.haagahelia.BookstoreMC.domain.Book;

@SpringBootTest
@AutoConfigureMockMvc
public class BookstoreRepositoryTest {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testGetEndpoint() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/books/")).andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.length()").isNumber())
		.andExpect(jsonPath("$[0].title").isString())
		.andExpect(jsonPath("$[0].author").isString())
		.andDo(print());
	}

	@Test
    public void testAddBookEndpoint() throws Exception {
        Book book = new Book("Title", "Author", 2025, 7781234, 20, null);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/books/"))
				.andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Book"))
                .andExpect(jsonPath("$.author").value("Test Author"))
                .andDo(print());
    }

}
