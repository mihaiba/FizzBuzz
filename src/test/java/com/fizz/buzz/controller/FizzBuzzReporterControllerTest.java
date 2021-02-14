package com.fizz.buzz.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fizz.buzz.model.FizzBuzzInput;
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

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class FizzBuzzReporterControllerTest {

    private static final String EXPECTED_1_20 =
            "{\"result\":{\"alfresco\":2,\"fizzbuzz\":1,\"fizz\":4,\"buzz\":3,\"an integer\":10}}";
    private static final String EXPECTED_1_30 =
            "{\"result\":{\"alfresco\":4,\"fizzbuzz\":1,\"fizz\":7,\"buzz\":4,\"an integer\":14}}";

    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest
    @MethodSource("generateTestInput")
    public void testController(int start, int end, ResultMatcher status, String expected) throws Exception {
        String actual = this.mockMvc.perform(post("/report")
                .content(toJsonString(FizzBuzzInput.builder()
                        .startInclusive(start)
                        .endInclusive(end)
                        .build()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status)
                .andReturn().getResponse().getContentAsString();
        assertEquals(expected, actual);
    }

    @Test
    public void invalidInputTest() throws Exception {
        this.mockMvc.perform(post("/report")
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

    private static Stream<Arguments> generateTestInput() {
        return Stream.of(
                Arguments.of(1, 20, status().isOk(), EXPECTED_1_20),
                Arguments.of(1, 30, status().isOk(), EXPECTED_1_30)
        );
    }
}
