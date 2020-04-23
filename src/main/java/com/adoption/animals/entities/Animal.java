package com.adoption.animals.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class Animal {

    private String id;
    private String name;
    private String type;
    private String breed;
    private String profilePicture;
    private List<Image> images;
    private List<Feature> features;

}
