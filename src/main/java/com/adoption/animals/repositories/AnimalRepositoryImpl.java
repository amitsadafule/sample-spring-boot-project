package com.adoption.animals.repositories;

import com.adoption.animals.entities.Animal;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class AnimalRepositoryImpl implements AnimalRepository {

    private final static List<Animal> DB = new ArrayList<>();

    public AnimalRepositoryImpl() {
        DB.addAll(Arrays.asList(
          Animal.builder()
              .name("Tuffy")
              .id("1")
              .type("dog")
              .breed("akita")
              .profilePicture("TuffyPic")
              .build(),
            Animal.builder()
                .name("Hush")
                .id("2")
                .type("dog")
                .breed("alaskan-malamute")
                .profilePicture("HushPic")
                .build(),
            Animal.builder()
                .name("Jumbo")
                .id("5")
                .type("rat")
                .breed("Tailless")
                .profilePicture("JumboPic")
                .build(),
            Animal.builder()
                .name("Kitty")
                .id("3")
                .type("cat")
                .breed("American Curl")
                .profilePicture("kittyPic")
                .build(),
            Animal.builder()
                .name("Milly")
                .id("4")
                .type("cat")
                .breed("Persian")
                .profilePicture("MillyPic")
                .build()
        ));
    }

    @Override
    public List<Animal> findAll(Example<Animal> query) {
        String breed = query.getProbe().getBreed();
        String type = query.getProbe().getType();
        String id = query.getProbe().getId();

        if(StringUtils.isEmpty(breed) && StringUtils.isEmpty(type) && StringUtils.isEmpty(id)) return DB;

        List<Animal> output = DB;
        if(!StringUtils.isEmpty(breed)) {
            output = searchByBreed(breed, output);
        }
        if(!StringUtils.isEmpty(type)) {
            output = searchByType(type, output);
        }
        if(!StringUtils.isEmpty(id)) {
            output = searchById(id, output);
        }
        return output;
    }

    private List<Animal> searchById(String id, List<Animal> referenceList) {
        return referenceList.stream()
            .filter(animal -> animal.getId().equals(id))
            .collect(Collectors.toList());
    }

    private List<Animal> searchByBreed(String breed, List<Animal> referenceList) {
        return referenceList.stream()
            .filter(animal -> animal.getBreed().equals(breed))
            .collect(Collectors.toList());
    }

    private List<Animal> searchByType(String type, List<Animal> referenceList) {
        return referenceList.stream()
            .filter(animal -> animal.getType().equals(type))
            .collect(Collectors.toList());
    }
}
