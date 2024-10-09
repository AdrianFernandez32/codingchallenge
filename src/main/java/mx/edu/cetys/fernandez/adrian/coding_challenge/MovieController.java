package mx.edu.cetys.fernandez.adrian.coding_challenge;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getMovieById(@PathVariable String id) {
        return ResponseEntity.ok(movieService.getMovieDetails(id));
    }

    @GetMapping("/multiple")
    public ResponseEntity<List<String>> getMultipleMovies(@RequestParam List<String> ids) {
        return ResponseEntity.ok(movieService.getMultipleMoviesDetails(ids));
    }
}
