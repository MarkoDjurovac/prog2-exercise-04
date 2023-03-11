package at.ac.fhcampuswien.fhmdb.provider.movie;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MockMovieProviderTest {
    private MockMovieProvider repository;

    @BeforeEach
    public void setupTest(){
            repository = new MockMovieProvider();
    }

    @Test
    public void shouldCreate(){
        assertNotNull(repository);
    }

    @Test
    public void does_provide_movie_list_with_content(){
        List<Movie> movies = repository.getMovies();
        assertNotNull(movies);
        assertTrue(movies.size() > 0);
    }

    @Test
    public void does_provided_movie_list_has_no_null_elements(){
        List<Movie> movies = repository.getMovies();
        for(int i=0; i<movies.size(); i++){
            assertNotNull(movies.get(i));
        }
    }
}
