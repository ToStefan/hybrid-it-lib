package hybrid.it.internship.library.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import hybrid.it.internship.library.web.dto.RentDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class IntegrationControllerTest {


    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @WithMockUser(value = "s")
    @Test
    public void testGetBookById() throws Exception {

        mockMvc.perform(get("http://localhost:8080/api/book/1").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @WithMockUser(value = "s")
    @Test
    public void testCreateRent() throws Exception {

        LocalDateTime ldt = LocalDateTime.now();
        RentDTO rentDto = RentDTO.builder()
                .userId(1L)
                .bookId(2L)
                .rentDate(ldt)
                .build();

        mockMvc.perform(post("http://localhost:8080/api/rent").content(objectMapper.writeValueAsString(rentDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        String bookResult = "{\"id\":2,\"author\":\"a2\",\"title\":\"t2\",\"totalCopies\":10,\"availableCopies\":9}";
        mockMvc.perform(get("http://localhost:8080/api/book/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.content().json(bookResult))
                .andExpect(status().isOk());
    }

}
