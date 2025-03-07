package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/api")
public class HelloController {


    @GetMapping("/movies/add")
    public String addMoviesGET(Model model) {
        model.addAttribute("movie", new Movie());
        return "movies";
    }

    @PostMapping("/movies/add")
    public String addMovie(@ModelAttribute Movie movie) {
        DemoApplication.movies.add(movie);
        return "redirect:/api/movies";
    }

    @GetMapping("/movies")
    public String viewFilms(Model model) {
        model.addAttribute("movies", DemoApplication.movies);
        return "recommendations";
    }

    @GetMapping("/recommendations")
    public String viewRecommendFilms(Model model) {
        Movie[] films = new Movie[DemoApplication.movies.size()];
        for (int i = 0; i < DemoApplication.movies.size(); i++) {
            films[i] = DemoApplication.movies.get(i);
        }
        Arrays.sort(films, Comparator.comparingInt(movie -> movie.rating));
        model.addAttribute("movies", films);
        return "recommendations";
    }


    @GetMapping("/error")
    public String handleError(Model model) {
        model.addAttribute("errorMessage", "Произошла ошибка. Пожалуйста, попробуйте позже.");
        return "error";
    }
}