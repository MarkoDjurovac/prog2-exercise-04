package at.ac.fhcampuswien.fhmdb.repositories.movie;

import at.ac.fhcampuswien.fhmdb.models.Movie;

import java.util.List;

public interface MovieRepository {
    public List<Movie> getMovies();
}
