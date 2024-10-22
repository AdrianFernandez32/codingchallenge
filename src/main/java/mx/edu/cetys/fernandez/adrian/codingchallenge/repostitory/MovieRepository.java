package mx.edu.cetys.fernandez.adrian.codingchallenge.repostitory;

import mx.edu.cetys.fernandez.adrian.codingchallenge.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
}
