package com.adoption.animals.repositories;

import com.adoption.animals.entities.Animal;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository {

    List<Animal> findAll(Example<Animal> query);
}
