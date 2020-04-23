package com.adoption.animals.domains;

import com.adoption.animals.entities.Animal;
import com.adoption.animals.presenters.BasicAnimalResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AnimalDOTest {

    @Test
    public void shouldReturnEmptyBasicAnimalsDetailsIfPassedValueIsNull() {
        assertThat(AnimalDO.getBasicDetails(null).size()).isEqualTo(0);
    }

    @Test
    public void shouldReturnEmptyBasicAnimalsDetailsIfPassedValueIsEmptyArray() {
        assertThat(AnimalDO.getBasicDetails(new ArrayList<>()).size()).isEqualTo(0);
    }

    @Test
    public void shouldReturnSameNumberOfBasicAnimalDetailsDataAsPassedListOfAnimals() {
        List<Animal> animals = Arrays.asList(
          Animal.builder().build(), Animal.builder().build(), Animal.builder().build()
        );

        assertThat(AnimalDO.getBasicDetails(animals).size()).isEqualTo(animals.size());
    }

    @Test
    public void shouldReturnValidBasicAnimalDetailsDataAsPassedListOfAnimals() {
        List<Animal> animals = Arrays.asList(
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

        List<BasicAnimalResponse> expectedValue = Arrays.asList(
            BasicAnimalResponse.builder()
                .breed(animals.get(0).getBreed())
                .id(animals.get(0).getId())
                .profilePicture(animals.get(0).getProfilePicture())
                .type(animals.get(0).getType())
                .build(),
            BasicAnimalResponse.builder()
                .breed(animals.get(1).getBreed())
                .id(animals.get(1).getId())
                .profilePicture(animals.get(1).getProfilePicture())
                .type(animals.get(1).getType())
                .build(),
            BasicAnimalResponse.builder()
                .breed(animals.get(2).getBreed())
                .id(animals.get(2).getId())
                .profilePicture(animals.get(2).getProfilePicture())
                .type(animals.get(2).getType())
                .build()
        );
        Assertions.assertEquals(AnimalDO.getBasicDetails(animals), expectedValue);
    }

    @Test
    public void shouldSkipNullBasicAnimalDetailsDataIfAnyPassedListOfAnimalsHasNull() {
        List<Animal> animals = Arrays.asList(
            Animal.builder().build(), null, Animal.builder().build()
        );

        assertThat(AnimalDO.getBasicDetails(animals).size()).isEqualTo(2);
    }
}