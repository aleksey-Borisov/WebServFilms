package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import java.util.Arrays;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/api")
public class HelloController {

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
//        Movie[] films = new Movie[DemoApplication.movies.size()];
//        for (int i = 0; i < DemoApplication.movies.size(); i++) {
//            films[i] = DemoApplication.movies.get(i);
//        }
//        Arrays.sort(films, Comparator.comparingInt(movie -> movie.rating));
        model.addAttribute("movies", DemoApplication.movies);
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