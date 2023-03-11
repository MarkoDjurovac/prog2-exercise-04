package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.repositories.movie.MockMovieRepository;
import at.ac.fhcampuswien.fhmdb.repositories.movie.MovieRepository;
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
import java.util.*;
import java.util.stream.Collectors;

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
    public JFXButton sortBtn;

    public List<Movie> allMovies;

    private final MovieRepository movieRepository = new MockMovieRepository();

    private final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allMovies = movieRepository.getMovies();
        observableMovies.addAll(allMovies);         // add dummy data to observable list

        // initialize UI stuff
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data

        genreComboBox.setPromptText("Filter by Genre");
        genreComboBox.getItems().addAll( Genre.values() );

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
        String searchQuery = searchField.getText().toLowerCase();
        Genre selectedGenre = genreComboBox.getSelectionModel().getSelectedItem();

        var filteredMovies = allMovies.stream()
                .filter(movie -> containsQueryString(movie, searchQuery) && movie.hasGenre(selectedGenre))
                .toList();

        observableMovies.addAll(filteredMovies);
    }

    static boolean containsQueryString(Movie movie, String query){
        if(query == null || query.isBlank()){
            return true;
        }
        return movie.getTitle().toLowerCase().contains(query.toLowerCase()) || movie.getDescription().toLowerCase().contains(query.toLowerCase());
    }
}