package mx.edu.cetys.fernandez.adrian.codingchallenge.service;

import mx.edu.cetys.fernandez.adrian.codingchallenge.model.Movie;
import mx.edu.cetys.fernandez.adrian.codingchallenge.repostitory.MovieRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final OpenAIService openAIService;

    public MovieService(MovieRepository movieRepository, OpenAIService openAIService) {
        this.movieRepository = movieRepository;
        this.openAIService = openAIService;
    }

    @Async
    public CompletableFuture<String> searchMovies(String title) throws Exception {
        // Simulamos búsqueda en TMDB (puedes usar RestTemplate para TMDB como ya lo tienes)
        Movie movie = new Movie(title);
        movieRepository.save(movie);

        // Obtener el resumen de OpenAI llamando a OpenAIService
        String summary = openAIService.getSummaryFromOpenAI(movie.getTitle());

        return CompletableFuture.completedFuture("Summary: " + summary);
    }
}
