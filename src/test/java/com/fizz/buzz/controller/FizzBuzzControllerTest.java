package com.fizz.buzz.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fizz.buzz.model.FizzBuzzInput;
import com.fizz.buzz.model.FizzBuzzOutput;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class FizzBuzzControllerTest {
    private static final List<String> EXPECTED_1_20 = ImmutableList.of("1", "2", "alfresco", "4", "buzz", "fizz", "7",
            "8", "fizz", "buzz", "11", "fizz", "alfresco", "14", "fizzbuzz", "16", "17", "fizz", "19", "buzz");
    private static final List<String> EXPECTED_1_30 = ImmutableList.of("1", "2", "alfresco", "4", "buzz", "fizz", "7",
            "8", "fizz", "buzz", "11", "fizz", "alfresco", "14", "fizzbuzz", "16", "17", "fizz", "19", "buzz", "fizz",
            "22", "alfresco", "fizz", "buzz", "26", "fizz", "28", "29", "alfresco");

    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest
    @MethodSource("generateTestInput")
    public void testController(int start, int end, ResultMatcher status, List<String> expected) throws Exception {
        String contentAsString = this.mockMvc.perform(post("/fizzbuzz")
                .content(toJsonString(FizzBuzzInput.builder()
                        .startInclusive(start)
                        .endInclusive(end)
                        .build()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status)
                .andReturn().getResponse().getContentAsString();
        FizzBuzzOutput actual = toObject(contentAsString);
        assertTrue(expected.containsAll(actual.getResult()));
    }

    @Test
    public void invalidInputTest() throws Exception {
        this.mockMvc.perform(post("/fizzbuzz")
                .content(toJsonString(FizzBuzzInput.builder()
                        .build()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    public static String toJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static FizzBuzzOutput toObject(final String content) {
        try {
            return new ObjectMapper().readValue(content, FizzBuzzOutput.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Stream<Arguments> generateTestInput() {
        return Stream.of(
                Arguments.of(1, 20, status().isOk(), EXPECTED_1_20),
                Arguments.of(1, 30, status().isOk(), EXPECTED_1_30)
        );
    }
}
