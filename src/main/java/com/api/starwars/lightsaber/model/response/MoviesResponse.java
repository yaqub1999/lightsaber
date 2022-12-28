package com.api.starwars.lightsaber.model.response;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class MoviesResponse {
    private String title;
    private Integer episode_id;
    private String director;
    private LocalDate release_date;
    private List<String> characters;
    private LocalDate created;
    private LocalDate edited;
    private String opening_crawl;
    private List<String> planets;
    private String producer;
    private List<String> species;
    private List<String> starships;
    private String url;
    private List<String> vehicles;
}
