package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Comparator;
@Controller
@RequestMapping("/api")
public class FilmsServControllers {

    @GetMapping("/movies/add")
    public String addMoviesGET(Model model) {
        Movie movie = new Movie();
        model.addAttribute("movie", movie);
        return "movies";
    }

    @PostMapping("/movies/add")
    public String addMovie(@ModelAttribute Movie movie) {
        if (!DemoApplication.movies.isEmpty()) {
            Movie lastMovie = DemoApplication.movies.get(DemoApplication.movies.size() - 1);
            movie.setId(lastMovie.getId() + 1);
        } else {
            movie.setId(0);
        }
        DemoApplication.movies.add(movie);
        return "redirect:/api/movies";
    }

    @GetMapping("/movies")
    public String viewFilms(Model model) {
        model.addAttribute("movies", DemoApplication.movies);
        return "recommendations";
    }


    @PostMapping("/movies/{id}/rating")
    public String updateRatingForm(@PathVariable int id, @RequestParam int rating) {

        for (Movie movie : DemoApplication.movies) {
            if (movie.getId() == id) {
                movie.setRating(rating);
            }
        }
        return "redirect:/api/movies";
    }



    @PostMapping("/movies/{id}/comment")
    public String updateComment(@PathVariable int id, @RequestParam String comment) {

        for (Movie movie : DemoApplication.movies) {
            if (movie.getId() == id) {
                movie.setComment(comment);
            }
        }
        return "redirect:/api/movies";
    }

    @GetMapping("/recommendations")
    public String viewRecommendFilms(Model model) {
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

        model.addAttribute("movies", sortedMovies );
        return "recommendations";
    }

    @GetMapping("/recommendations/best")
    public String viewBestFilms(Model model) {
        List<Movie> bestFilms = new ArrayList<>();

        for (Movie movie : DemoApplication.movies) {
            // Если рейтинг фильма равен 5, добавляем его в список
            if (movie.getRating() == 5) {
                bestFilms.add(movie);
            }
        }

        model.addAttribute("movies", bestFilms );
        return "recommendations";
    }


    @GetMapping("/error")
    public String handleError(@RequestParam(required = false) String errorMessage,
                              @RequestParam(required = false) String errorCode,
                              Model model) {
        if (errorMessage == null) {
            errorMessage = "Произошла ошибка. Пожалуйста, попробуйте позже.";
        }
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("errorCode", errorCode);
        return "error";
    }
}