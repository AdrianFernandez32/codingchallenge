package mx.edu.cetys.fernandez.adrian.codingchallenge.service;

import mx.edu.cetys.fernandez.adrian.codingchallenge.model.Movie;
import mx.edu.cetys.fernandez.adrian.codingchallenge.repostitory.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieDatabaseService {

    private final MovieRepository movieRepository;

    public MovieDatabaseService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public List<Movie> findAllMovies() {
        return movieRepository.findAll();
    }

    public Movie findMovieById(Long id) {
        return movieRepository.findById(id).orElse(null);
    }
}
