package mx.edu.cetys.fernandez.adrian.codingchallenge;

import mx.edu.cetys.fernandez.adrian.codingchallenge.model.Movie;
import mx.edu.cetys.fernandez.adrian.codingchallenge.service.MovieService;
import mx.edu.cetys.fernandez.adrian.codingchallenge.service.OpenAIService;
import mx.edu.cetys.fernandez.adrian.codingchallenge.service.TMDBService;
import mx.edu.cetys.fernandez.adrian.codingchallenge.service.MovieDatabaseService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MovieServiceTest {

	@Mock
	private TMDBService tmdbService;

	@Mock
	private OpenAIService openAIService;

	@Mock
	private MovieDatabaseService movieDatabaseService;

	@InjectMocks
	private MovieService movieService;

	@Test
	public void testProcessMovie() throws Exception {
		String title = "Inception";
		String mockTMDBResponse = "{...}";
		String mockOpenAISummary = "This is a brief summary of Inception.";

		when(tmdbService.searchMovieByTitle(title)).thenReturn(mockTMDBResponse);

		when(openAIService.getSummaryFromOpenAI(title)).thenReturn(mockOpenAISummary);

		Movie mockMovie = new Movie(title);
		when(movieDatabaseService.saveMovie(mockMovie)).thenReturn(mockMovie);

		String result = movieService.processMovie(title).get();

		assertEquals("TMDB Response: " + mockTMDBResponse + "\nSummary: " + mockOpenAISummary, result);
	}
}
