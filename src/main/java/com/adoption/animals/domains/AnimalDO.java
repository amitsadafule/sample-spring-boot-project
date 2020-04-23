package com.adoption.animals.domains;

import com.adoption.animals.entities.Animal;
import com.adoption.animals.presenters.AnimalsRequest;
import com.adoption.animals.presenters.BasicAnimalResponse;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AnimalDO {

    public static Example<Animal> createGetBasicDetailQuery(AnimalsRequest animalsRequest) {
        return Example.of(
            Animal.builder()
                .breed(animalsRequest.getBreed())
                .type(animalsRequest.getType())
                .id(animalsRequest.getId())
                .build()
        );
    }

    public static List<BasicAnimalResponse> getBasicDetails(List<Animal> animals) {
        if(animals == null) {
            return new ArrayList<>();
        }

        return animals.stream()
            .map(AnimalDO::from)
            .filter(animal -> animal != null)
            .collect(Collectors.toList());
    }

    private static BasicAnimalResponse from(Animal animal) {
        if(animal == null) return null;

        return BasicAnimalResponse.builder()
            .type(animal.getType())
            .profilePicture(animal.getProfilePicture())
            .id(animal.getId())
            .breed(animal.getBreed())
            .build();
    }
}
