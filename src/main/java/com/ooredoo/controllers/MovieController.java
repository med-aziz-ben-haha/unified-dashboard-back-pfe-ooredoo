package com.ooredoo.controllers;

import com.ooredoo.entities.Movie;
import com.ooredoo.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    MovieService movieService;

    // http://localhost:8089/ooredoo/movie/list-all
    @GetMapping("/list-all")
    @ResponseBody
    public Collection<Movie> getAllMovies() { return movieService.getAllMovies(); }

}
