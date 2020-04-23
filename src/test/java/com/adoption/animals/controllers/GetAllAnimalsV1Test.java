package com.adoption.animals.controllers;

import com.adoption.animals.exceptions.ExceptionType;
import com.adoption.animals.presenters.AnimalsRequest;
import com.adoption.animals.presenters.AnimalsResponse;
import com.adoption.animals.presenters.BasicAnimalResponse;
import com.adoption.animals.services.AnimalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class GetAllAnimalsV1Test {

    private static final String ALL_ANIMALS_BASE_URL = "/v1/animals";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AnimalService animalService;

    private AnimalsResponse animalsResponse;

    @BeforeEach
    public void setUp() {
        animalsResponse = AnimalsResponse.builder()
            .animals(Arrays.asList(
                BasicAnimalResponse.builder()
                    .breed("Golden Retriever")
                    .type("dog")
                    .id("TEST ID 1")
                    .profilePicture("http://test-link")
                    .build(),
                BasicAnimalResponse.builder()
                    .breed("Yorkshire Terrier")
                    .type("dog")
                    .id("TEST ID 2")
                    .profilePicture("http://test-link-2")
                    .build(),
                BasicAnimalResponse.builder()
                    .breed("Yorkshire Terrier")
                    .type("cat")
                    .id("TEST ID 2")
                    .profilePicture("http://test-link-2")
                    .build(),
                BasicAnimalResponse.builder()
                    .breed("Yorkshire Terrier")
                    .type("cat")
                    .id("TEST ID 2")
                    .profilePicture("http://test-link-2")
                    .build()
            ))
            .build();
    }

    @Test
    public void shouldReturnAllAnimalsV1() throws Exception {

        when(animalService.getAnimals(any(AnimalsRequest.class))).thenReturn(animalsResponse);

        this.mockMvc
            .perform(get(ALL_ANIMALS_BASE_URL))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.animals").isArray())
            .andExpect(jsonPath("$.animals", hasSize(4)));
    }

    @Test
    public void shouldThrowBindExceptionIfTypeHasLessThan3Chars() throws Exception {

        this.mockMvc
            .perform(get(ALL_ANIMALS_BASE_URL)
                .param("type", "ab"))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("code").value(is(ExceptionType.BIND_ERROR.getErrorCode())));
    }

    @Test
    public void shouldReturnOKIfTypeHasGreaterThan3Chars() throws Exception {

        this.mockMvc
            .perform(get(ALL_ANIMALS_BASE_URL)
                .param("type", "abcder"))
            .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnOKIfTypeHas3Chars() throws Exception {

        this.mockMvc
            .perform(get(ALL_ANIMALS_BASE_URL)
                .param("type", "abc"))
            .andExpect(status().isOk());
    }

    @Test
    public void shouldThrowBindExceptionIfBreedHasLessThan3Chars() throws Exception {

        this.mockMvc
            .perform(get(ALL_ANIMALS_BASE_URL)
                .param("breed", "ab"))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("code").value(is(ExceptionType.BIND_ERROR.getErrorCode())));
    }

    @Test
    public void shouldReturnOKIfBreedHasGreaterThan3Chars() throws Exception {

        this.mockMvc
            .perform(get(ALL_ANIMALS_BASE_URL)
                .param("breed", "abcder"))
            .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnOKIfBreedHas3Chars() throws Exception {

        this.mockMvc
            .perform(get(ALL_ANIMALS_BASE_URL)
                .param("breed", "abc"))
            .andExpect(status().isOk());
    }
}