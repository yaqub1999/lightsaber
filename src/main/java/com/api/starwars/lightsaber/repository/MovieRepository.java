package com.api.starwars.lightsaber.repository;

import com.api.starwars.lightsaber.model.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

     List<Movie> findByMovieName(String movieName);
     Movie findByMovieId(Integer movieId);

}
