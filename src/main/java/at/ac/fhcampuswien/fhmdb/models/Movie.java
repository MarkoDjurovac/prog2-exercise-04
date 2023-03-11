package at.ac.fhcampuswien.fhmdb.models;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    private String title;
    private String description;
    private List<Genre> genres;

    public Movie(String title, String description, List<Genre> genres) {
        if(title == null || title.isBlank()){
            throw new IllegalArgumentException("Title may not be null or empty");
        }
        if(description == null || description.isBlank()){
            throw new IllegalArgumentException("Description may not be null or empty");
        }
        if(genres == null){
            throw new IllegalArgumentException("Genre-List may not be null");
        }
        this.title = title;
        this.description = description;
        this.genres = new ArrayList<>(genres);
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Genre> getGenres() {
        return new ArrayList<>(genres);
    }

    public boolean hasGenre(Genre genre){
        if(genre == null || genre == Genre.ALL){
            return true;
        }
        return genres.contains(genre);
    }
}
