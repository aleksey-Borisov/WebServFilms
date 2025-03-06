package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/movies")
    public String showMovies(Model model) {
        model.addAttribute("movie", new Movie());
        return "movies";
    }

    @PostMapping("/movies")
    public String addMovie(@ModelAttribute Movie movie) {
        // Логика для добавления фильма
        return "redirect:/api/movies"; // Перенаправление на страницу со списком фильмов
    }

    @GetMapping("/error")
    public String handleError(Model model) {
        model.addAttribute("errorMessage", "Произошла ошибка. Пожалуйста, попробуйте позже.");
        return "error";
    }
}