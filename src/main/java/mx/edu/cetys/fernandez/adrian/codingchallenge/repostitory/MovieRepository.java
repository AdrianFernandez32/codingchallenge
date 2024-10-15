package mx.edu.cetys.fernandez.adrian.codingchallenge.repostitory;

import mx.edu.cetys.fernandez.adrian.codingchallenge.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
