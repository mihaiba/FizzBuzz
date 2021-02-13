package com.fizz.buzz;

import com.sun.org.apache.xpath.internal.Arg;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class FizzBuzzTest {

    private FizzBuzz sut;

    @BeforeEach
    public void init() {
        sut = new FizzBuzz();
    }

    @ParameterizedTest
    @MethodSource("generateTestInput")
    public void testConvertValue(int input, String expected) {
        String actual = sut.map(input);
        Assertions.assertEquals(expected, actual);
    }

    private static Stream<Arguments> generateTestInput() {
        return Stream.of(
                Arguments.of(1, "1"),
                Arguments.of(3, "alfresco"),
                Arguments.of(5, "buzz"),
                Arguments.of(6, "fizz"),
                Arguments.of(8,"8"),
                Arguments.of(9, "fizz"),
                Arguments.of(10, "buzz"),
                Arguments.of(13, "alfresco"),
                Arguments.of(15, "fizzbuzz")
        );
    }
}
