package com.api.starwars.lightsaber.service;

import com.api.starwars.lightsaber.model.dto.PeopleDTO;
import com.api.starwars.lightsaber.model.entity.People;
import com.api.starwars.lightsaber.model.response.PeopleResponse;
import com.api.starwars.lightsaber.repository.PeopleRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class PeopleService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PeopleRepository repository;
    private final Logger LOGGER = LoggerFactory.getLogger("Logger");


    public PeopleResponse getPeople(String characterName) {
        RestTemplate restTemplate = new RestTemplate();
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        restTemplate = restTemplateBuilder.build();
        PeopleResponse response = null;
        try {
            response = restTemplate.getForObject("http://localhost:7029/people?name=" + characterName,
                    PeopleResponse.class);

            if (this.repository.findByPeopleName(characterName) == null && response != null) {
                PeopleDTO inserir = new PeopleDTO();
                inserir.setPeopleName(response.getName());
                this.repository.save(mapper.map(inserir, People.class));
            }

        } catch (RestClientException e) {
            LOGGER.error("Erro na chamada HTTP GET da url http://localhost:7029/people?name=");
            e.printStackTrace();
        }

        return response;
    }

    public PeopleDTO include(PeopleDTO people) throws Exception {
        if (people.getPeopleId() == null) {
            People peopleSave = mapper.map(people, People.class);

            return mapper.map(this.repository.save(peopleSave), PeopleDTO.class);
        } else {
            throw new Exception("O campo peopleId não deve ser informado");
        }

    }

    public PeopleDTO edit(PeopleDTO people) throws Exception {
        if (people.getPeopleId() != null && people.getPeopleName() != null && !people.getPeopleName().isBlank()) {
            People peopleSave = mapper.map(people, People.class);

            return mapper.map(this.repository.save(peopleSave), PeopleDTO.class);
        } else {
            throw new Exception("O campo peopleId  deve ser informado");
        }
    }

    public PeopleDTO findByName(String people) throws Exception {
        if (people != null && !people.isBlank()) {
            return mapper.map(this.repository.findByPeopleName(people), PeopleDTO.class);
        } else {
            throw new Exception("O campo peopleName deve ser informado");
        }
    }


    public PeopleDTO findByPeopleId(Integer idPeople) throws Exception {
       if (idPeople != null) {
           return mapper.map(this.repository.findByPeopleId(idPeople), PeopleDTO.class);
       } else {
           LOGGER.info("O campo idPeople não pode ser null");
           throw new Exception("O campo idPeople não pode ser null");
       }
    }
}
