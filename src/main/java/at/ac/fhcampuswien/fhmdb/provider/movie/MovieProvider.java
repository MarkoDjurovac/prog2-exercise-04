package at.ac.fhcampuswien.fhmdb.provider.movie;

import at.ac.fhcampuswien.fhmdb.models.Movie;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface MovieProvider {
    List<Movie> getMovies();

    List<Movie> getMoviesWithQuery(Map<String, String> queryMap);
}
