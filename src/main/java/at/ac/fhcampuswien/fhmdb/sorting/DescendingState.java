package at.ac.fhcampuswien.fhmdb.sorting;

import at.ac.fhcampuswien.fhmdb.controller.HomeController;
import at.ac.fhcampuswien.fhmdb.model.Movie;

import java.util.Comparator;
import java.util.List;

public class DescendingState implements SortingState {
    @Override
    public void next(HomeController context) {
        context.setSortState(new UnsortedState());
        context.updateSortButton("Sort");
    }

    @Override
    public void sort(List<Movie> movies) {
        movies.sort(Comparator.comparing(Movie::getTitle).reversed());
    }
}