package mx.edu.cetys.fernandez.adrian.codingchallenge;

import mx.edu.cetys.fernandez.adrian.codingchallenge.repostitory.MovieRepository;
import mx.edu.cetys.fernandez.adrian.codingchallenge.service.MovieService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.when;

@SpringBootTest
public class MovieServiceTest {

	@Mock
	private MovieRepository movieRepository;

	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	private MovieService movieService;

	@Test
	public void testSearchMovies() throws Exception {
		String title = "Inception";
		String mockApiResponse = "{...}"; // Mock TMDB response
		when(restTemplate.getForObject("...", String.class)).thenReturn(mockApiResponse);

		movieService.searchMovies(title);

		// Add assertions here
	}
}
