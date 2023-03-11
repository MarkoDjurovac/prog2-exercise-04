package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HomeControllerTest {
    @Test
    public void contains_string_check_returns_true_if_checked_for_null_or_empty_string(){
        List<Genre> genres = new ArrayList<>();
        genres.add(Genre.ROMANCE);
        Movie mov = new Movie("MovieTitle", "Description of the movie", genres);
        assertTrue(HomeController.containsQueryString(mov, null));
        assertTrue(HomeController.containsQueryString(mov, " "));
        assertTrue(HomeController.containsQueryString(mov, "   "));
    }

    @Test
    public void contains_string_check_returns_true_if_checking_for_value_present_inside_title(){
        List<Genre> genres = new ArrayList<>();
        genres.add(Genre.ROMANCE);
        Movie mov = new Movie("MovieTitle", "Description of the movie", genres);
        assertTrue(HomeController.containsQueryString(mov, "MovieT"));
    }
    @Test
    public void contains_string_check_returns_true_if_checking_for_value_present_inside_title_case_insensitive(){
        List<Genre> genres = new ArrayList<>();
        genres.add(Genre.ROMANCE);
        Movie mov = new Movie("MovieTitle", "Description of the movie", genres);
        assertTrue(HomeController.containsQueryString(mov, "mOvIeT"));
    }

    @Test
    public void contains_string_check_returns_true_if_checking_for_value_present_inside_description(){
        List<Genre> genres = new ArrayList<>();
        genres.add(Genre.ROMANCE);
        Movie mov = new Movie("MovieTitle", "Description of the movie", genres);
        assertTrue(HomeController.containsQueryString(mov,"Descri"));
        assertTrue(HomeController.containsQueryString(mov, "of the"));
        assertTrue(HomeController.containsQueryString(mov,"the movie"));
    }

    @Test
    public void contains_string_check_returns_true_if_checking_for_value_present_inside_description_case_insensitive(){
        List<Genre> genres = new ArrayList<>();
        genres.add(Genre.ROMANCE);
        Movie mov = new Movie("MovieTitle", "Description of the movie", genres);
        assertTrue(HomeController.containsQueryString(mov, "DeScRi"));
        assertTrue(HomeController.containsQueryString(mov, "oF tHe"));
        assertTrue(HomeController.containsQueryString(mov, "tHe moViE"));
    }

    @Test
    public void contains_string_check_returns_false_if_checking_for_value_not_present_inside_description_or_title(){
        List<Genre> genres = new ArrayList<>();
        genres.add(Genre.ROMANCE);
        Movie mov = new Movie("MovieTitle", "Description of the movie", genres);
        assertFalse(HomeController.containsQueryString(mov, "Definitely not in the title!!!==="));
    }
}