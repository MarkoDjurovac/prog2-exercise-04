package at.ac.fhcampuswien.fhmdb.provider.movie;

import at.ac.fhcampuswien.fhmdb.models.Movie;

import java.io.IOException;
import java.util.List;

public interface MovieProvider {
    List<Movie> getMovies();
}
