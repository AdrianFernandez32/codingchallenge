package mx.edu.cetys.fernandez.adrian.codingchallenge;

import mx.edu.cetys.fernandez.adrian.codingchallenge.service.TMDBService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@TestPropertySource("classpath:application.properties")
public class TMDBServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private TMDBService tmdbService;

    @Value("${TMDB_API_KEY}")
    private String tmdbApiKey;

    @Test
    public void testSearchMovieByTitle() {
        String mockResponse = "{ \"results\": [ { \"title\": \"Inception\" } ] }";
        String url = String.format("https://api.themoviedb.org/3/search/movie?api_key=%s&query=Inception", tmdbApiKey);

        when(restTemplate.getForObject(url, String.class)).thenReturn(mockResponse);

        String result = tmdbService.searchMovieByTitle("Inception");
        assertEquals(mockResponse, result);
    }
}
