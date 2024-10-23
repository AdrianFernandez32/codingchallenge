package mx.edu.cetys.fernandez.adrian.codingchallenge;

import mx.edu.cetys.fernandez.adrian.codingchallenge.model.Movie;
import mx.edu.cetys.fernandez.adrian.codingchallenge.service.MovieDatabaseService;
import mx.edu.cetys.fernandez.adrian.codingchallenge.service.MovieService;
import mx.edu.cetys.fernandez.adrian.codingchallenge.service.OpenAIService;
import mx.edu.cetys.fernandez.adrian.codingchallenge.service.TMDBService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MovieServiceTest {

    @Mock
    private TMDBService tmdbService;

    @Mock
    private OpenAIService openAIService;

    @Mock
    private MovieDatabaseService movieDatabaseService;

    @InjectMocks
    private MovieService movieService;

    public MovieServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testProcessMovie() throws Exception {
        String movieTitle = "Inception";
        String tmdbResponse = "TMDB Response";
        String openAISummary = "Summary: This is a summary of the movie Inception";

        when(tmdbService.searchMovieByTitle(movieTitle)).thenReturn(tmdbResponse);
        when(openAIService.getSummaryFromOpenAI(movieTitle)).thenReturn(openAISummary);

        CompletableFuture<String> resultFuture = movieService.processMovie(movieTitle);

        String result = resultFuture.get();

        verify(movieDatabaseService).saveMovie(any(Movie.class));
        assertEquals("TMDB Response: " + tmdbResponse + "\nSummary: " + openAISummary, result);
    }

    @Test
    public void testGetAllMovies() throws Exception {
        List<Movie> movies = Arrays.asList(new Movie("Inception"), new Movie("Interstellar"));
        when(movieDatabaseService.findAllMovies()).thenReturn(movies);

        CompletableFuture<String> resultFuture = movieService.getAllMovies();

        String result = resultFuture.get();

        assertEquals(movies.toString(), result);
    }
}
