package com.api.starwars.lightsaber.model.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id", unique = true, nullable = false)
    private Integer movieId;

    @Column(name = "movie_name", nullable = false)
    private String movieName;

    @Column(name = "movie_description", nullable = false)
    private String movieDescription;

    @ManyToMany(mappedBy = "movie")
    private List<People> people;

    public List<People> getPeople() {
        return people;
    }

    public void setPeople(List<People> people) {
        this.people = people;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieDescription() {
        return movieDescription;
    }

    public void setMovieDescription(String movieDescription) {
        this.movieDescription = movieDescription;
    }
}
