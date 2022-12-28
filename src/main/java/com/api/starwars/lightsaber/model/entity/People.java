package com.api.starwars.lightsaber.model.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "people")
public class People {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "people_id")
    private Integer peopleId;
    @Column(name = "people_name", nullable = false)
    private String peopleName;

    @ManyToMany
    @JoinTable(name="people_movie", joinColumns =
            @JoinColumn(name = "people_id"), inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    private List<Movie> movie;

    public List<Movie> getMovie() {
        return movie;
    }

    public void setMovie(List<Movie> movie) {
        this.movie = movie;
    }

    public Integer getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(Integer peopleId) {
        this.peopleId = peopleId;
    }

    public String getPeopleName() {
        return peopleName;
    }

    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName;
    }
}
