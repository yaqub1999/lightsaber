package com.api.starwars.lightsaber.repository;


import com.api.starwars.lightsaber.model.entity.People;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeopleRepository extends JpaRepository<People, Integer> {
    People findByPeopleName(String peopleName);
    People findByPeopleId(Integer peopleId);

}
