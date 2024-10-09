package mx.edu.cetys.fernandez.adrian.coding_challenge;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private MovieService movieService;

    @Test
    void testGetMovieDetails() {
        String movieId = "550";
        String expectedResponse = "{\"id\": 550, \"title\": \"Fight Club\"}";

        Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(String.class)))
                .thenReturn(expectedResponse);

        String actualResponse = movieService.getMovieDetails(movieId);

        Assertions.assertEquals(expectedResponse, actualResponse);
    }
}
