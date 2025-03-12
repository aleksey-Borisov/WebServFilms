package com.example.controllers;

import com.example.model.Movie;
import com.example.DemoApplication;
import com.example.service.MovieService;

import com.example.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class MovieController {
    private final MovieService movieService;
    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies/add")
    public String addMovies(Model model) {
        Movie movie = new Movie();
        model.addAttribute("movie", movie);
        return "movies";
    }

    @PostMapping("/movies/add")
    public String addMoviesForm(@ModelAttribute Movie movie) {
        movieService.addMovie(movie);
        return "redirect:/movies";
    }

    @GetMapping("/movies")
    public String viewFilms(Model model) {
        model.addAttribute("movies", DemoApplication.movies);
        return "recommendations";
    }


    @PostMapping("/movies/{id}/rating")
    public String updateRatingForm(@PathVariable int id, @RequestParam int rating) {

        movieService.updateRating( id,  rating);
        return "redirect:/movies";
    }



    @PostMapping("/movies/{id}/comment")
    public String updateComment(@PathVariable int id, @RequestParam String comment) {

        movieService.updateComment( id, comment);
        return "redirect:/movies";
    }


    @GetMapping("/recommendations")
    public String viewRecommendFilms(Model model) {

        model.addAttribute("movies", movieService.getSortedMoviesByRating() );
        return "recommendations";
    }


        @GetMapping("/recommendations/best")
    public String viewBestFilms(Model model) {
        model.addAttribute("movies", movieService.getBestMovies() );
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