package com.adoption.animals.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Image {
    private int priority;
    private String url;
    private String description;
}
