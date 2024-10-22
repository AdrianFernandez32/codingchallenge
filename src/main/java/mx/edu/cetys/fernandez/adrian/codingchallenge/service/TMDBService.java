package mx.edu.cetys.fernandez.adrian.codingchallenge.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TMDBService {

    @Value("${tmdb_apikey}")
    private String tmdbApiKey;

    private final RestTemplate restTemplate;

    public TMDBService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String searchMovieByTitle(String title) {
        String url = String.format("https://api.themoviedb.org/3/search/movie?api_key=%s&query=%s", tmdbApiKey, title);
        return restTemplate.getForObject(url, String.class);
    }
}
