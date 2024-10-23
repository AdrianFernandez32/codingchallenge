package mx.edu.cetys.fernandez.adrian.codingchallenge.service;

import mx.edu.cetys.fernandez.adrian.codingchallenge.model.Movie;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class MovieService {

    private final TMDBService tmdbService;
    private final OpenAIService openAIService;
    private final MovieDatabaseService movieDatabaseService;

    public MovieService(TMDBService tmdbService, OpenAIService openAIService, MovieDatabaseService movieDatabaseService) {
        this.tmdbService = tmdbService;
        this.openAIService = openAIService;
        this.movieDatabaseService = movieDatabaseService;
    }

    @Async
    public CompletableFuture<String> processMovie(String title) throws Exception {
        String tmdbResponse = tmdbService.searchMovieByTitle(title);

        Movie movie = new Movie(title);
        movieDatabaseService.saveMovie(movie);

        String summary = openAIService.getSummaryFromOpenAI(movie.getTitle());

        return CompletableFuture.completedFuture("TMDB Response: " + tmdbResponse + "\nSummary: " + summary);
    }
}

