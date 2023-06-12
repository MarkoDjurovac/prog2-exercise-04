package at.ac.fhcampuswien.fhmdb.sorting;

import at.ac.fhcampuswien.fhmdb.controller.HomeController;
import at.ac.fhcampuswien.fhmdb.model.Movie;

import java.util.List;

public interface SortingState {
    void next(HomeController context);
    void sort(List<Movie> movies);
}