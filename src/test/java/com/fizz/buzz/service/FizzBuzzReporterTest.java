package com.fizz.buzz.service;

import com.fizz.buzz.service.FizzBuzz;
import com.fizz.buzz.service.FizzBuzzReporter;
import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        Map<String, Long> actual = sut.generateReport(input);
        boolean allMatch = expected.entrySet().stream()
                .allMatch(entry -> entry.getValue().equals(actual.get(entry.getKey())));
        assertTrue(allMatch);
    }

    private static Stream<Arguments> generateTestInput() {
        return Stream.of(
                Arguments.of(IntStream.empty().boxed().collect(toList()), ImmutableMap.of()),
                Arguments.of(IntStream.rangeClosed(1, 20).boxed().collect(toList()),
                        ImmutableMap.of("alfresco", 2L,
                                "fizzbuzz", 1L,
                                "fizz", 4L,
                                "buzz", 3L,
                                "an integer", 10L)),
                Arguments.of(IntStream.rangeClosed(1, 45).boxed().collect(toList()),
                        ImmutableMap.of("alfresco", 14L,
                                "fizzbuzz", 2L,
                                "fizz", 8L,
                                "buzz", 5L,
                                "an integer", 16L))
        );
    }
}
