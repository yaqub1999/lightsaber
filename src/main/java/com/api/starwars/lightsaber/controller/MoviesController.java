package com.api.starwars.lightsaber.controller;

import com.api.starwars.lightsaber.model.dto.MovieDTO;
import com.api.starwars.lightsaber.model.response.MoviesResponse;
import com.api.starwars.lightsaber.service.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MoviesController {

    @Autowired
    private MoviesService service;

    @GetMapping
    public ResponseEntity<MoviesResponse> getMovieById(@RequestParam Integer movieId) {
            MoviesResponse response = this.service.getMovieById(movieId);
            if (response != null) {
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
    }

    @PostMapping
    public ResponseEntity<MovieDTO> include(@RequestBody MovieDTO dto) {
        try {
            return new ResponseEntity<>(this.service.include(dto), HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping
    public ResponseEntity<MovieDTO> edit(@RequestBody MovieDTO dto) {
        try {
            return new ResponseEntity<>(this.service.edit(dto), HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/find/id")
    public ResponseEntity<MovieDTO> findById(@RequestParam Integer id) {
        try {
            return ResponseEntity.ok(this.service.findById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/find/name")
    public ResponseEntity<List<MovieDTO>> findByName(@RequestParam String name) {
        try {
            return ResponseEntity.ok(this.service.findByName(name));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/find/")
    public ResponseEntity<List<MovieDTO>> findAll() {
        return ResponseEntity.ok(this.service.findAll());
    }

    @GetMapping("/filter/character")
    public ResponseEntity<List<MoviesResponse>> getMovieByCharacterName(@RequestParam String characterName) {
        List<MoviesResponse> response = this.service.getMoviesByCharacter(characterName);
        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/filter/title")
    public ResponseEntity<MoviesResponse> getMovieByTitle(@RequestParam String title) {
        MoviesResponse response = this.service.getMovieByTitle(title);
        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/filter/episode")
    public ResponseEntity<MoviesResponse> getMovieByEpisodeId(@RequestParam Integer episodeId) {
        MoviesResponse response = this.service.getMovieByEpisodeId(episodeId);
        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

}
