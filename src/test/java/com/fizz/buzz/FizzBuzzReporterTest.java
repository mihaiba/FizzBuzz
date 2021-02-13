package com.fizz.buzz;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FizzBuzzReporterTest {

    private FizzBuzzReporter sut;

    @BeforeEach
    public void init() {
        //would have mocked the FizzBuzz service, but it's too easy to construct it :)
        sut = new FizzBuzzReporter(new FizzBuzz());
    }

    @ParameterizedTest
    @MethodSource("generateTestInput")
    public void test(List<Integer> input, Map<String, Long> expected) {
        assertEquals(expected, sut.generateReport(input));

    }

    private static Stream<Arguments> generateTestInput() {
        return Stream.of(
                Arguments.of(ImmutableList.of(), ImmutableMap.of())
        );
    }
}
