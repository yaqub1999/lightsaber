package com.api.starwars.lightsaber.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class PeopleDTO {
    private Integer peopleId;
    private String peopleName;
    private List<MovieDTO> movie;
}
