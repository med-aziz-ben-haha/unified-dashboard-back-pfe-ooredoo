package com.ooredoo.repositories;

import com.ooredoo.entities.Movie;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.Collection;

public interface MovieRepository extends Neo4jRepository<Movie, Long> {


    @Query("MATCH (n:Movie) RETURN n")
    Collection<Movie> getAllMovies();
}
