package mx.edu.cetys.fernandez.adrian.codingchallenge.service;

import mx.edu.cetys.fernandez.adrian.codingchallenge.model.Movie;
import mx.edu.cetys.fernandez.adrian.codingchallenge.repostitory.MovieRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final RestTemplate restTemplate;

    private final String tmdbApiKey = "8829509002394ab72b2199f25ff94ce7";
    //SPRING_AI_OPENAI_API_KEY

    public MovieService(MovieRepository movieRepository, RestTemplate restTemplate) {
        this.movieRepository = movieRepository;
        this.restTemplate = restTemplate;
    }

    @Async
    public CompletableFuture<String> searchMovies(String title) throws Exception {
        String url = String.format("https://api.themoviedb.org/3/search/movie?api_key=%s&query=%s", tmdbApiKey, title);
        String response = restTemplate.getForObject(url, String.class);

        // Parse response, store in DB, and call LLM
        Movie movie = new Movie(title);
        movieRepository.save(movie);

        // Call OpenAI for a summary (mocked for now)
        String summary = getSummaryFromLLM(movie.getTitle());

        return CompletableFuture.completedFuture("Results: " + response + "\n Summary: " + summary);
    }

    private String getSummaryFromLLM(String movieTitle) {
        // Call to OpenAI API will be here
        return "This is a summary for the movie: " + movieTitle;
    }
}
