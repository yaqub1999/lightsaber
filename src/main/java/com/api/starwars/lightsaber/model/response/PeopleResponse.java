package com.api.starwars.lightsaber.model.response;

import lombok.Data;

import java.util.List;
@Data
public class PeopleResponse {
    private String name;
    private List<String> films;
    private String birth_year;
    private String eye_color;
    private String gender;
    private String hair_color;
    private String height;
    private String mass;
    private String skin_color;
    private String homeworld;
    private List<String> species;
    private List<String> starships;
    private List<String> vehicles;
    private String url;
    private String created;
    private String edited;
}
