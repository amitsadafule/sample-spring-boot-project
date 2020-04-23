package com.adoption.animals.controllers;

import com.adoption.animals.presenters.AnimalsRequest;
import com.adoption.animals.presenters.AnimalsResponse;
import com.adoption.animals.services.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/animals")
public class AnimalV1Controller {

    @Autowired
    private AnimalService animalService;

    @GetMapping
    public AnimalsResponse getAnimals(@Valid AnimalsRequest animalsRequest) {
        return animalService.getAnimals(animalsRequest);
    }
}
