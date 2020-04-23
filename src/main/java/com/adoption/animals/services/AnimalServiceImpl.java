package com.adoption.animals.services;

import com.adoption.animals.domains.AnimalDO;
import com.adoption.animals.entities.Animal;
import com.adoption.animals.presenters.AnimalsRequest;
import com.adoption.animals.presenters.AnimalsResponse;
import com.adoption.animals.repositories.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalServiceImpl implements AnimalService {

    private AnimalRepository animalRepository;

    @Autowired
    public AnimalServiceImpl(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Override
    public AnimalsResponse getAnimals(AnimalsRequest animalsRequest) {
        Example<Animal> animalQuery = AnimalDO.createGetBasicDetailQuery(animalsRequest);
        List<Animal> animals = animalRepository.findAll(animalQuery);
        return AnimalsResponse.builder()
            .animals(AnimalDO.getBasicDetails(animals))
            .count(animals.size())
            .build();

    }
}
