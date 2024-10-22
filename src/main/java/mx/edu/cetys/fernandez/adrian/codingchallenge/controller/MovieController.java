package mx.edu.cetys.fernandez.adrian.codingchallenge.controller;

import mx.edu.cetys.fernandez.adrian.codingchallenge.service.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    // Endpoint para buscar películas, guardar en la base de datos y obtener un resumen
    @GetMapping("/search/{title}")
    public CompletableFuture<String> processMovie(@PathVariable String title) throws Exception {
        return movieService.processMovie(title);
    }

    // Endpoint para obtener todas las películas en la base de datos
    @GetMapping
    public CompletableFuture<String> getAllMovies() {
        return movieService.getAllMovies();
    }
}
