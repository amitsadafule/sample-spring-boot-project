package com.adoption.animals.presenters;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class AnimalsResponse {

    private int count;
    private List<BasicAnimalResponse> animals;
}
