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

    @GetMapping("/search/{title}")
    public CompletableFuture<String> processMovie(@PathVariable String title) throws Exception {
        return movieService.processMovie(title);
    }
}
