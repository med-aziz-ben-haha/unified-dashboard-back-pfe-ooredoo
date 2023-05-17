package com.ooredoo.entities;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.springframework.data.annotation.Id;

import java.util.List;

@NodeEntity
public class User {

    @Id
    @GraphId
    private Long id;
    private String name;
    private Integer age;

    @Relationship(type = "RATED", direction = Relationship.INCOMING)
    private List<Movie> movies;

    public List<Movie> getMovies() {
        return movies;
    }

    public User() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
}
