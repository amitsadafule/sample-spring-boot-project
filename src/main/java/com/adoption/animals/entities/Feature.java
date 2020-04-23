package com.adoption.animals.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Feature {
    private String key;
    private String value;
}
