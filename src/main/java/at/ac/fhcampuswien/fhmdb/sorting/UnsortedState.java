package at.ac.fhcampuswien.fhmdb.sorting;

import at.ac.fhcampuswien.fhmdb.controller.HomeController;
import at.ac.fhcampuswien.fhmdb.model.Movie;

import java.util.List;

public class UnsortedState implements SortingState {

    @Override
    public void next(HomeController context) {
        context.setSortState(new AscendingState());
        context.updateSortButton("Sort (desc)");
    }

    @Override
    public void sort(List<Movie> movies) {
        // No sorting needed
    }
}