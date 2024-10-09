package mx.edu.cetys.fernandez.adrian.coding_challenge;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class MovieService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String API_KEY = "88295e9002394ab72b2199f25ff94ce7";

    public String getMovieDetails(String movieId) {
        String url = String.format("https://api.themoviedb.org/3/movie/%s?api_key=%s", movieId, API_KEY);
        return restTemplate.getForObject(url, String.class);
    }

    public List<String> getMultipleMoviesDetails(List<String> movieIds) {
        List<String> moviesDetails = new ArrayList<>();

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            List<Future<String>> futures = movieIds.stream()
                    .map(id -> executor.submit(() -> getMovieDetails(id)))
                    .toList();

            for (Future<String> future : futures) {
                moviesDetails.add(future.get());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return moviesDetails;
    }
}
