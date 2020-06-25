package at.technikumwien;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Tag("integration-test")
class CarRepositoryTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;



    @Test
    public void test_retrieve_1() throws Exception {
        var requestBuilder = MockMvcRequestBuilders
                .get("/resources/cars/1")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc
                .perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void test_Retrieve() throws Exception {
        var requestBuilder = MockMvcRequestBuilders
                .get("/resources/cars")
                .accept(MediaType.APPLICATION_JSON);

        var response = mockMvc
                .perform(requestBuilder)
                .andReturn().getResponse();

        var persons = objectMapper.readValue(
                response.getContentAsString(),
                new TypeReference<List<Car>>() {
                } //List<Person>.class nicht möglich
        );
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertThat(response.getContentType()).containsIgnoringCase(MediaType.APPLICATION_JSON_VALUE);
        assertEquals(1, persons.size());
    }

    @Test
    public void test_Retrieve_all() throws Exception {
        var requestBuilder = MockMvcRequestBuilders
                .get("/resources/cars?all=true")
                .accept(MediaType.APPLICATION_JSON);

        var response = mockMvc
                .perform(requestBuilder)
                .andReturn().getResponse();

        var persons = objectMapper.readValue(
                response.getContentAsString(),
                new TypeReference<List<Car>>() {
                } //List<Person>.class nicht möglich
        );
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertThat(response.getContentType()).containsIgnoringCase(MediaType.APPLICATION_JSON_VALUE);
        assertEquals(2, persons.size());
    }
}
