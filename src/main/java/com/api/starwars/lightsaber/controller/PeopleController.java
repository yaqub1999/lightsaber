package com.api.starwars.lightsaber.controller;


import com.api.starwars.lightsaber.model.dto.PeopleDTO;
import com.api.starwars.lightsaber.model.response.PeopleResponse;
import com.api.starwars.lightsaber.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    private PeopleService service;

    @GetMapping
    public PeopleResponse getPeopleByName(@RequestParam String name) {
        return this.service.getPeople(name);
    }

    @GetMapping("/find/name")
    public ResponseEntity<PeopleDTO> findByName(@RequestParam String name) {
        try {
            PeopleDTO response = this.service.findByName(name);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/filter/name")
    public ResponseEntity<PeopleResponse> getByName(@RequestParam String name) {
        try {
            PeopleResponse response = this.service.getPeople(name);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/find/id")
    public ResponseEntity<PeopleDTO> findByPeopleId(@RequestParam Integer peopleId) {
        try {
            PeopleDTO response = this.service.findByPeopleId(peopleId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<PeopleDTO> include(@RequestBody PeopleDTO people) {
        try {
            return ResponseEntity.ok(this.service.include(people));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(people);
        }
    }

    @PutMapping
    public ResponseEntity<PeopleDTO> edit(@RequestBody PeopleDTO people) {
        try {
            return ResponseEntity.ok(this.service.edit(people));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(people);
        }
    }
}


