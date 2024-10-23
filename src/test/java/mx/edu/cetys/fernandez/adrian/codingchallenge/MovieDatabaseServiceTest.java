package mx.edu.cetys.fernandez.adrian.codingchallenge;

import mx.edu.cetys.fernandez.adrian.codingchallenge.model.Movie;
import mx.edu.cetys.fernandez.adrian.codingchallenge.repostitory.MovieRepository;
import mx.edu.cetys.fernandez.adrian.codingchallenge.service.MovieDatabaseService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MovieDatabaseServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieDatabaseService movieDatabaseService;

    @Test
    public void testSaveMovie() {
        Movie movie = new Movie("Inception");
        movieDatabaseService.saveMovie(movie);
        verify(movieRepository).save(movie);
    }
}
