package at.ac.fhcampuswien.fhmdb.provider.movie;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.google.gson.Gson;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class MovieAPI implements MovieProvider {
    private static final String BASE_URL = "https://prog2.fh-campuswien.ac.at";
    private static final String ENDPOINT_MOVIES = "/movies";
    private static final String ENDPOINT_MOVIE = "/movie";
    private OkHttpClient client;
    private Gson gson;

    public MovieAPI() {
        client = new OkHttpClient();
        gson = new Gson();
    }

    @Override
    public List<Movie> getMovies(){
        HttpUrl url = HttpUrl.parse(BASE_URL + ENDPOINT_MOVIES);
        Request request = new Request.Builder()
                .url(url)
                .addHeader("User-Agent", "http.agent")
                .build();
        try (Response response = client.newCall(request).execute()) {
            String json = response.body().string();
            return Arrays.asList(gson.fromJson(json, Movie[].class));
        }
        catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Movie> getMoviesWithQuery(String query) {
        HttpUrl url = HttpUrl.parse(BASE_URL + ENDPOINT_MOVIES).newBuilder()
                .addQueryParameter("query", query)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("User-Agent", "http.agent")
                .build();
        try (Response response = client.newCall(request).execute()) {
            String json = response.body().string();
            return Arrays.asList(gson.fromJson(json, Movie[].class));
        }
        catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }

    // TODO: implement getMovies with parameters for filtering
}
