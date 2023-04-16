package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.provider.movie.MovieAPI;
import at.ac.fhcampuswien.fhmdb.provider.movie.MovieProvider;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.util.Duration;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.Year;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class HomeController implements Initializable {
    private final static String ASCENDING = "Sort (asc)";

    private final static String DESCENDING = "Sort (desc)";

    @FXML
    public JFXButton searchBtn;

    @FXML
    public TextField searchField;

    @FXML
    public JFXListView movieListView;

    @FXML
    public JFXComboBox<Genre> genreComboBox;

    @FXML
    public JFXComboBox<Integer> yearComboBox;

    @FXML
    public JFXComboBox<Double> ratingComboBox;

    @FXML
    public JFXButton sortBtn;

    public List<Movie> allMovies;

    private final MovieProvider movieAPIProvider = new MovieAPI();

    private final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allMovies = movieAPIProvider.getMovies();
        observableMovies.addAll(allMovies);         // add dummy data to observable list

        // initialize UI stuff
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data

        genreComboBox.setPromptText("Filter by Genre");
        genreComboBox.getItems().addAll( Genre.values() );

        yearComboBox.setPromptText("Filter by Release Year");
        int[] years = IntStream.range( 1930, Year.now().getValue() + 1 ).toArray(); // generate years from 1930 to current year
        yearComboBox.getItems().addAll( Arrays.stream(years).boxed().collect(Collectors.toList()) );
        yearComboBox.getItems().add(0);

        ratingComboBox.setPromptText("Filter by Rating");
        double[] ratings = DoubleStream.iterate( 0.0, d -> d + 0.5 ).limit( 21 ).toArray(); // generate ratings from 0.0 to 10.0 in steps of 0.5
        ratingComboBox.getItems().addAll( Arrays.stream(ratings).boxed().collect(Collectors.toList()) );

        sortBtn.setOnAction(this::toggleSortOrder);
        searchBtn.setOnAction(this::searchAction);

        PauseTransition searchDebounce = new PauseTransition(Duration.millis(500));
        searchDebounce.setOnFinished(this::searchAction);
        searchField.textProperty().addListener((observable, old, newVal) -> {
            searchDebounce.playFromStart();
        });
    }

    void toggleSortOrder(ActionEvent actionEvent) {
        if(sortBtn.getText().equals(ASCENDING)) {
            observableMovies.sort(Comparator.comparing(Movie::getTitle));
            sortBtn.setText(DESCENDING);
        } else {
            observableMovies.sort(Comparator.comparing(Movie::getTitle).reversed());
            sortBtn.setText(ASCENDING);
        }
    }

    void searchAction(ActionEvent actionEvent) {
        observableMovies.clear();

        var filteredMovies = movieAPIProvider.getMoviesWithQuery(constructQueryMap());

        observableMovies.addAll(filteredMovies);
    }

    static boolean containsQueryString(Movie movie, String query){
        if(query == null || query.isBlank()){
            return true;
        }
        return movie.getTitle().toLowerCase().contains(query.toLowerCase()) || movie.getDescription().toLowerCase().contains(query.toLowerCase());
    }

    // returns the person who appears most often in the most often in the mainCast of the given movies.
    public String getMostPopularActor(List<Movie> movies) {
        return movies.stream()
                .flatMap(movie -> movie.getMainCast().stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("");
    }

    // filters on the longest movie title of the passed movies and returns the number of letters in the title.
    public int getLongestMovieTitle(List<Movie> movies) {
        return movies.stream()
                .mapToInt(movie -> movie.getTitle().length())
                .max()
                .orElse(0);
    }

    // returns the number of movies of a given director
    public long countMoviesFrom(List<Movie> movies, String director) {
        return movies.stream()
                .filter(movie -> movie.getDirectors().stream().anyMatch(director::equals))
                .count();
    }

    // returns those movies that were released between two given years.
    public List<Movie> getMoviesBetweenYears(List<Movie> movies, int startYear, int endYear) {
        return movies.stream()
                .filter(movie -> movie.getReleaseYear() >= startYear && movie.getReleaseYear() <= endYear)
                .collect(Collectors.toList());
    }

    private Map<String, String> constructQueryMap(){
        var queryMap = new HashMap<String, String>();

        String searchQuery = searchField.getText().toLowerCase();
        queryMap.put("query", searchQuery);

        Genre selectedGenre = genreComboBox.getSelectionModel().getSelectedItem();
        if(selectedGenre != null && !selectedGenre.toString().equals("ALL")) queryMap.put("genre", selectedGenre.toString());

        Integer selectedYear = yearComboBox.getSelectionModel().getSelectedItem();
        if(selectedYear != null && selectedYear > 0) queryMap.put("releaseYear", selectedYear+"");

        Double selectedRating = ratingComboBox.getSelectionModel().getSelectedItem();
        if(selectedRating != null && selectedRating > 0) queryMap.put("ratingFrom", selectedRating.toString());

        return queryMap;
    }
}