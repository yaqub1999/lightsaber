package com.api.starwars.lightsaber.model.dto;

import com.api.starwars.lightsaber.model.entity.Movie;
import lombok.Data;

import java.util.List;

@Data
public class MovieDTO {
    private Integer movieId;
    private String movieName;
    private String movieDescription;
    private List<PeopleDTO> people;
}
