package com.adoption.animals.services;

import com.adoption.animals.entities.Animal;
import com.adoption.animals.presenters.AnimalsRequest;
import com.adoption.animals.presenters.AnimalsResponse;
import com.adoption.animals.presenters.BasicAnimalResponse;
import com.adoption.animals.repositories.AnimalRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AnimalServiceImplTest {

    private List<Animal> DB;

    @Mock
    private AnimalRepository animalRepository;

    private AnimalService animalService;

    @BeforeEach
    public void setUp() {
        DB = Arrays.asList(
            Animal.builder()
                .breed("abc")
                .type("t1")
                .id("1")
                .name("xyz1")
                .profilePicture("test_picture1")
                .build(),
            Animal.builder()
                .breed("abcd")
                .type("t2")
                .id("2")
                .name("xyz2")
                .profilePicture("test_picture2")
                .build(),
            Animal.builder()
                .breed("abce")
                .type("t3")
                .id("3")
                .name("xyz3")
                .profilePicture("test_picture3")
                .build()
        );

        animalService = new AnimalServiceImpl(animalRepository);
    }

    @Test
    public void ShouldReturnListOfBasicAnimalResponseWithCount() {
        when(animalRepository.findAll(any())).thenReturn(DB);
        AnimalsResponse actual = animalService.getAnimals(AnimalsRequest.builder().build());

        assertThat(actual.getCount()).isEqualTo(DB.size());
        assertThat(actual.getAnimals().size()).isEqualTo(DB.size());

        List<BasicAnimalResponse> expectedValue = Arrays.asList(
            BasicAnimalResponse.builder()
                .breed(DB.get(0).getBreed())
                .id(DB.get(0).getId())
                .profilePicture(DB.get(0).getProfilePicture())
                .type(DB.get(0).getType())
                .build(),
            BasicAnimalResponse.builder()
                .breed(DB.get(1).getBreed())
                .id(DB.get(1).getId())
                .profilePicture(DB.get(1).getProfilePicture())
                .type(DB.get(1).getType())
                .build(),
            BasicAnimalResponse.builder()
                .breed(DB.get(2).getBreed())
                .id(DB.get(2).getId())
                .profilePicture(DB.get(2).getProfilePicture())
                .type(DB.get(2).getType())
                .build()
        );
        Assertions.assertEquals(actual.getAnimals(), expectedValue);
    }
}