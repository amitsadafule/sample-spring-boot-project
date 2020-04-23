package com.adoption.animals.presenters;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BasicAnimalResponse {

    private String id;
    private String type;
    private String breed;
    private String profilePicture;
}
