package at.ac.fhcampuswien.fhmdb.model;

import static org.junit.jupiter.api.Assertions.*;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class MovieTest{
    @Test
    public void does_create(){
        assertDoesNotThrow(()->{
            List<Genre> genres = new ArrayList<>();
            Movie mov = new Movie("MovieTitle", "Description of the movie", genres);
        });
    }
    @Test
    public void does_contain_constructor_data(){
        List<Genre> genres = new ArrayList<>();
        genres.add(Genre.ADVENTURE);
        Movie mov = new Movie("MovieTitle", "Description of the movie", genres);
        assertEquals("MovieTitle", mov.getTitle());
        assertEquals("Description of the movie", mov.getDescription());
        assertTrue(mov.getGenres().contains(Genre.ADVENTURE));
    }

    @Test
    public void constructor_does_not_allow_for_null_title(){
        assertThrows(IllegalArgumentException.class, ()->{
           new Movie(null, "Description", new ArrayList<>());
        });
    }

    @Test
    public void constructor_does_not_allow_for_empty_title(){
        assertThrows(IllegalArgumentException.class, ()->{
            new Movie("", "Description", new ArrayList<>());
        });
    }

    @Test
    public void constructor_does_not_allow_for_whitespace_title(){
        assertThrows(IllegalArgumentException.class, ()->{
            new Movie("   ", "Description", new ArrayList<>());
        });
    }

    @Test
    public void constructor_does_not_allow_for_null_genres(){
        assertThrows(IllegalArgumentException.class, ()->{
            new Movie("Title", "Description", null);
        });
    }

    @Test
    public void constructor_does_not_allow_for_null_description(){
        assertThrows(IllegalArgumentException.class, ()->{
            new Movie("Title", null, new ArrayList<>());
        });
    }

    @Test
    public void constructor_does_not_allow_for_empty_description(){
        assertThrows(IllegalArgumentException.class, ()->{
            new Movie("Title", "", new ArrayList<>());
        });
    }

    @Test
    public void constructor_does_not_allow_for_whitespace_description(){
        assertThrows(IllegalArgumentException.class, ()->{
            new Movie("Title", "  ", new ArrayList<>());
        });
    }

    @Test
    public void has_genre_returns_true_if_queried_for_null(){
        List<Genre> genres = new ArrayList<>();
        Movie mov = new Movie("MovieTitle", "Description of the movie", genres);
        assertTrue(mov.hasGenre(null));
    }

    @Test
    public void has_genre_returns_true_if_queried_for_genre_all(){
        List<Genre> genres = new ArrayList<>();
        Movie mov = new Movie("MovieTitle", "Description of the movie", genres);
        assertTrue(mov.hasGenre(Genre.ALL));
    }

    @Test
    public void has_genre_returns_false_if_no_genres_are_set_and_not_queried_for_null(){
        List<Genre> genres = new ArrayList<>();
        Movie mov = new Movie("MovieTitle", "Description of the movie", genres);
        assertFalse(mov.hasGenre(Genre.ACTION));
    }

    @Test
    public void has_genre_returns_false_if_does_not_have_matching_genre(){
        List<Genre> genres = new ArrayList<>();
        genres.add(Genre.ROMANCE);
        Movie mov = new Movie("MovieTitle", "Description of the movie", genres);
        assertFalse(mov.hasGenre(Genre.ACTION));
    }

    @Test
    public void has_genre_returns_true_if_does_have_matching_genre(){
        List<Genre> genres = new ArrayList<>();
        genres.add(Genre.ROMANCE);
        Movie mov = new Movie("MovieTitle", "Description of the movie", genres);
        assertTrue(mov.hasGenre(Genre.ROMANCE));
    }

    @Test
    public void genre_list_can_not_be_altered_via_direct_access(){
        List<Genre> genres = new ArrayList<>();
        genres.add(Genre.ROMANCE);
        Movie mov = new Movie("MovieTitle", "Description of the movie", genres);
        assertTrue(mov.hasGenre(Genre.ROMANCE));
        mov.getGenres().remove(Genre.ROMANCE);
        assertTrue(mov.getGenres().contains(Genre.ROMANCE));
    }

    @Test
    public void genre_list_can_not_be_altered_by_altering_data_source(){
        List<Genre> genres = new ArrayList<>();
        genres.add(Genre.ROMANCE);
        Movie mov = new Movie("MovieTitle", "Description of the movie", genres);
        assertTrue(mov.hasGenre(Genre.ROMANCE));
        genres.remove(Genre.ROMANCE);
        assertTrue(mov.getGenres().contains(Genre.ROMANCE));
    }

    @Test
    public void contains_string_check_returns_true_if_checked_for_null_or_empty_string(){
        List<Genre> genres = new ArrayList<>();
        genres.add(Genre.ROMANCE);
        Movie mov = new Movie("MovieTitle", "Description of the movie", genres);
        assertTrue(mov.containsQueryString(null));
        assertTrue(mov.containsQueryString(" "));
        assertTrue(mov.containsQueryString("   "));
    }

    @Test
    public void contains_string_check_returns_true_if_checking_for_value_present_inside_title(){
        List<Genre> genres = new ArrayList<>();
        genres.add(Genre.ROMANCE);
        Movie mov = new Movie("MovieTitle", "Description of the movie", genres);
        assertTrue(mov.containsQueryString("MovieT"));
    }
    @Test
    public void contains_string_check_returns_true_if_checking_for_value_present_inside_title_case_insensitive(){
        List<Genre> genres = new ArrayList<>();
        genres.add(Genre.ROMANCE);
        Movie mov = new Movie("MovieTitle", "Description of the movie", genres);
        assertTrue(mov.containsQueryString("mOvIeT"));
    }

    @Test
    public void contains_string_check_returns_true_if_checking_for_value_present_inside_description(){
        List<Genre> genres = new ArrayList<>();
        genres.add(Genre.ROMANCE);
        Movie mov = new Movie("MovieTitle", "Description of the movie", genres);
        assertTrue(mov.containsQueryString("Descri"));
        assertTrue(mov.containsQueryString("of the"));
        assertTrue(mov.containsQueryString("the movie"));
    }

    @Test
    public void contains_string_check_returns_true_if_checking_for_value_present_inside_description_case_insensitive(){
        List<Genre> genres = new ArrayList<>();
        genres.add(Genre.ROMANCE);
        Movie mov = new Movie("MovieTitle", "Description of the movie", genres);
        assertTrue(mov.containsQueryString("DeScRi"));
        assertTrue(mov.containsQueryString("oF tHe"));
        assertTrue(mov.containsQueryString("tHe moViE"));
    }

    @Test
    public void contains_string_check_returns_false_if_checking_for_value_not_present_inside_description_or_title(){
        List<Genre> genres = new ArrayList<>();
        genres.add(Genre.ROMANCE);
        Movie mov = new Movie("MovieTitle", "Description of the movie", genres);
        assertFalse(mov.containsQueryString("Definitely not in the title!!!==="));
    }
}
