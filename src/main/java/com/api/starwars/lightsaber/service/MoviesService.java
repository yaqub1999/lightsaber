package com.api.starwars.lightsaber.service;

import com.api.starwars.lightsaber.model.dto.MovieDTO;
import com.api.starwars.lightsaber.model.entity.Movie;
import com.api.starwars.lightsaber.model.response.MoviesResponse;
import com.api.starwars.lightsaber.model.response.PeopleResponse;
import com.api.starwars.lightsaber.model.response.SearchMoviesResponse;
import com.api.starwars.lightsaber.repository.MovieRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class MoviesService {

    private final Logger LOGGER = LoggerFactory.getLogger("Logger");

    @Autowired
    private PeopleService peopleService;

    @Autowired
    private MovieRepository repository;

    @Autowired
    private ModelMapper mapper;

    public MoviesResponse getMovieById(Integer movieId) {
        RestTemplate restTemplate = new RestTemplate();
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        restTemplate = restTemplateBuilder.build();
        MoviesResponse response = null;
        try {
            LOGGER.info("CHAMANDO API");
            response = restTemplate.getForObject("http://localhost:7029/movies?movieId=" + movieId.toString(),
                    MoviesResponse.class);
        } catch (RestClientException e) {
            LOGGER.error("Erro na chamada HTTP GET da url https://swapi.dev/api/films/");
            e.printStackTrace();
        }
        return response;
    }

    public List<MoviesResponse> getMoviesByCharacter(String characterName) {

        RestTemplate restTemplate = new RestTemplate();
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        restTemplate = restTemplateBuilder.build();

        List<MoviesResponse> response =  restTemplate.getForObject("http://localhost:7029/movies/filter/character?characterName=" + characterName, List.class);

         return response;
    }

    public MoviesResponse getMovieByTitle(String title) {
            RestTemplate restTemplate = new RestTemplate();
            RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
            restTemplate = restTemplateBuilder.build();
            MoviesResponse response = null;
            try {
                response = restTemplate.getForObject("http://localhost:7029/movies/filter/title?title=" + title,
                        MoviesResponse.class);
                if (repository.findByMovieName(title).isEmpty() && response != null) {
                    MovieDTO dto = new MovieDTO();
                    dto.setMovieName(response.getTitle());
                    dto.setMovieDescription(response.getUrl());
                    this.repository.save(mapper.map(dto, Movie.class));
                }

            } catch (RestClientException e) {
                LOGGER.error("Erro na chamada HTTP GET da url https://swapi.dev/api/films/");
                e.printStackTrace();
            }

            return response;
    }

    public MoviesResponse getMovieByEpisodeId(Integer episodeId) {
        RestTemplate restTemplate = new RestTemplate();
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        restTemplate = restTemplateBuilder.build();
        MoviesResponse response = null;
        try {
            response = restTemplate.getForObject("http://localhost:7029/movies/filter/episode?episodeId=" + episodeId,
                    MoviesResponse.class);
        } catch (RestClientException e) {
            LOGGER.error("Erro na chamada HTTP GET da url https://swapi.dev/api/films/");
            e.printStackTrace();
        }

        return response;
    }

    public List<MovieDTO> findByName(String name) throws Exception {
        if (name != null && !name.isBlank()) {
            return mapper.map(this.repository.findByMovieName(name), List.class);
        } else {
            throw new Exception("O campo movieName deve ser informado");
        }
    }

    public List<MovieDTO> findAll() {
        List<MovieDTO> list = mapper.map(this.repository.findAll(), List.class);
        return list;
    }

    public MovieDTO findById(Integer id) throws Exception{
        if (id != null) {
            return mapper.map(this.repository.findByMovieId(id), MovieDTO.class);
        } else {
            LOGGER.error("O id do filme deve ser informado");
            throw new Exception("O id do filme deve ser informado");
        }
    }

    public MovieDTO include(MovieDTO movie) throws Exception{
        if (movie.getMovieId() == null) {
            Movie movieSave = mapper.map(movie, Movie.class);

            return mapper.map(this.repository.save(movieSave), MovieDTO.class);
        } else {
            throw new Exception("O campo movieId não deve ser informado");
        }
    }

    public MovieDTO edit(MovieDTO movie) throws Exception{
        if (movie.getMovieId() != null) {
            Movie movieSave = mapper.map(movie, Movie.class);

            return mapper.map(this.repository.save(movieSave), MovieDTO.class);
        } else {
            throw new Exception("O campo mvoieId deve ser informado");
        }
    }

}
