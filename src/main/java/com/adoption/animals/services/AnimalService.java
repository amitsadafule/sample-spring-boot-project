package com.adoption.animals.services;

import com.adoption.animals.presenters.AnimalsRequest;
import com.adoption.animals.presenters.AnimalsResponse;

public interface AnimalService {
    AnimalsResponse getAnimals(AnimalsRequest animalsRequest);
}
