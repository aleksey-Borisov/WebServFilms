package com.example.service;

import com.example.DemoApplication;
import com.example.model.Movie;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
public class MovieService {
    public List<Movie> movies = new ArrayList<>();
    private int nextId = 0;


    public void addMovie(Movie movie) {
        if (!DemoApplication.movies.isEmpty()) {
            Movie lastMovie = DemoApplication.movies.get(DemoApplication.movies.size() - 1);
            movie.setId(lastMovie.getId() + 1);
        } else {
            movie.setId(0);
        }
        DemoApplication.movies.add(movie);
    }


    public List<Movie> getAllMovies() {
        return movies;
    }


    public void updateRating(int id, int rating) {
        if (rating < 6 || rating > 0) {

            for (Movie movie : DemoApplication.movies) {
                if (movie.getId() == id) {
                    movie.setRating(rating);
                }
            }
        }
    }


    public void updateComment(int id, String comment) {
        for (Movie movie : DemoApplication.movies) {
            if (movie.getId() == id) {
                movie.setComment(comment);
            }
        }
    }


    public List<Movie> getSortedMoviesByRating() {
        Movie[] films = new Movie[DemoApplication.movies.size()];
        DemoApplication.movies.toArray(films);

        for (int i = 0; i < films.length - 1; i++) {
            for (int j = 0; j < films.length - i - 1; j++) {
                if (films[j].getRating() < films[j + 1].getRating()) {
                    Movie temp = films[j];
                    films[j] = films[j + 1];
                    films[j + 1] = temp;
                }
            }
        }

        List<Movie> sortedMovies = Arrays.asList(films);
        return sortedMovies;
    }


    public List<Movie> getBestMovies() {
        List<Movie> bestFilms = new ArrayList<>();
        for (Movie movie : DemoApplication.movies) {
            // Если рейтинг фильма равен 5, добавляем его в список
            if (movie.getRating() == 5) {
                bestFilms.add(movie);
            }
        }
        return bestFilms;
    }
}