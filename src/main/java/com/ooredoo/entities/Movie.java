package com.ooredoo.entities;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.springframework.data.annotation.Id;

@NodeEntity
public class Movie {

    @Id
    @GraphId
    private Long id;
    private String title;
    private String director;

    public Movie() {
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }
}
