package com.adoption.animals.presenters;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ImageResponse {

    private int priority;
    private String description;
    private String url;
}
