package com.ooredoo.services;

import com.ooredoo.entities.Movie;
import com.ooredoo.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public Collection<Movie> getAllMovies() {
        return movieRepository.getAllMovies();
    }
}
