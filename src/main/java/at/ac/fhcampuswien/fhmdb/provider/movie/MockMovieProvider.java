package at.ac.fhcampuswien.fhmdb.provider.movie;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MockMovieProvider implements MovieProvider {
    @Override
    public List<Movie> getMovies() {
        List<Movie> movies = new ArrayList<>();

        movies.add(new Movie("Pirates Of The Caribbean", "Blacksmith Will Turner teams up with eccentric pirate \"Captain\" Jack Sparrow to save his love, the governor's daughter, from Jack's former pirate allies, who are now undead.", new ArrayList<>(Arrays.asList(Genre.ADVENTURE, Genre.ACTION, Genre.FANTASY))));
        movies.add(new Movie("Avatar", "A paraplegic Marine dispatched to the moon Pandora on a unique mission becomes torn between following his orders and protecting the world he feels is his home.", new ArrayList<>(Arrays.asList(Genre.ADVENTURE, Genre.ACTION, Genre.FANTASY))));
        movies.add(new Movie("Chucky", "After a vintage Chucky doll turns up at a suburban yard sale, an idyllic American town is thrown into chaos as a series of horrifying murders begin to expose the town's hypocrisies and secrets..", new ArrayList<>(Arrays.asList(Genre.HORROR, Genre.THRILLER))));
        movies.add(new Movie("John Wick", "An ex-hit-man comes out of retirement to track down the gangsters that killed his dog and took his car.", new ArrayList<>(Arrays.asList( Genre.ACTION, Genre.CRIME, Genre.THRILLER))));
        movies.add(new Movie("Bruce Almighty", "A whiny news reporter is given the chance to step into God's shoes.", new ArrayList<>(Arrays.asList(Genre.COMEDY, Genre.FANTASY))));
        movies.add(new Movie("The Matrix", "When a beautiful stranger leads computer hacker Neo to a forbidding underworld, he discovers the shocking truth--the life he knows is the elaborate deception of an evil cyber-intelligence.", new ArrayList<>(Arrays.asList(Genre.ACTION, Genre.SCIENCE_FICTION))));
        movies.add(new Movie("Shrek", "A mean lord exiles fairytale creatures to the swamp of a grumpy ogre, who must go on a quest and rescue a princess for the lord in order to get his land back.", new ArrayList<>(Arrays.asList( Genre.ADVENTURE, Genre.COMEDY))));
        movies.add(new Movie("Titanic", "A seventeen-year-old aristocrat falls in love with a kind but poor artist aboard the luxurious, ill-fated R.M.S. Titanic.", new ArrayList<>(Arrays.asList(Genre.DRAMA, Genre.ROMANCE))));
        movies.add(new Movie("Pearl Harbor", "A tale of war and romance mixed in with history. The story follows two lifelong friends and a beautiful nurse who are caught up in the horror of an infamous Sunday morning in 1941.", new ArrayList<>(Arrays.asList(Genre.DRAMA, Genre.HISTORICAL))));
        movies.add(new Movie("Our Blooming Youth", "A story about a prince who receives a book about ghost stories containing all kinds of curses of life and feels that the curse is happening one by one", new ArrayList<>(Arrays.asList(Genre.DRAMA, Genre.HISTORICAL, Genre.MYSTERY))));
        movies.add(new Movie("Der Schuh des Manitu", "The hilarious adventures of two best friends, Apache chief Abahachi and cowboy Ranger, in the Wild West.", new ArrayList<>(Arrays.asList(Genre.COMEDY, Genre.WESTERN))));
        movies.add(new Movie("Jurassic Park", "A whiny news reporter is given the chance to step into God's shoes.", new ArrayList<>(Arrays.asList(Genre.ACTION, Genre.SCIENCE_FICTION))));
        movies.add(new Movie("The Simpsons Movie", "After Homer pollutes the town's water supply, Springfield is encased in a gigantic dome by the EPA and the Simpsons are declared fugitives.", new ArrayList<>(Arrays.asList(Genre.COMEDY))));
        movies.add(new Movie("Scream", "A year after the murder of her mother, a teenage girl is terrorized by a new killer, who targets the girl and her friends by using horror films as part of a deadly game.", new ArrayList<>(Arrays.asList(Genre.HORROR, Genre.MYSTERY))));

        return movies;
    }
}
