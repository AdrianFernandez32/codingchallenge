package mx.edu.cetys.fernandez.adrian.coding_challenge.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/search/{title}")
    public String searchMovies(@PathVariable String title) throws Exception {
        return movieService.searchMovies(title);
    }
}