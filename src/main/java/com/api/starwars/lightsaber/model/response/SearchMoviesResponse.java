package com.api.starwars.lightsaber.model.response;

import lombok.Data;

import java.util.List;

@Data
public class SearchMoviesResponse {
    private Integer count;
    private Integer next;
    private Integer previous;
    private List<MoviesResponse> results;
}
