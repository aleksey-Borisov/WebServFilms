package com.example;
import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
    public static ArrayList<Movie> movies = new ArrayList<>();
    public static void main(String[] args) {

//        ArrayList<Integer> filmsArray = new ArrayList<>();
        SpringApplication.run(DemoApplication.class, args);
    }

}