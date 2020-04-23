package com.adoption.animals.presenters;

import lombok.*;

import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnimalsRequest {

    @Size(min = 3, message = "error.get.animals.types.short.length")
    private String type;

    private String id;

    @Size(min = 3, message = "error.get.animals.breed.short.length")
    private String breed;
}
