package com.example.movie.controllers;

import com.example.movie.entities.Movie;
import com.example.movie.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping(value = "/movie")
    public void createMovie(@RequestBody Movie movie) {
        movieService.createMovie(movie);
    }

    @GetMapping(value = "/movie")
    public ResponseEntity<ArrayList<Movie>> findAllMovies() {
        ArrayList<Movie> allMovies = movieService.findAllMovies();
        return ResponseEntity.ok(allMovies);
    }

    @GetMapping(value = "/movie/{id}")
    public ResponseEntity<Movie> findMovieById(@PathVariable int id) {
        Movie movie = movieService.findMovieById(id);
        return ResponseEntity.ok(movie);
    }

    @PutMapping(value = "/movie/{id}")
    public void updateMovie(@RequestBody Movie movie, @PathVariable int id) {
        movieService.updateMovie(id, movie);
    }

    @DeleteMapping(value = "/movie/{id}")
    public void deleteMovie(@PathVariable int id) {
        movieService.deleteMovieById(id);
    }
}
