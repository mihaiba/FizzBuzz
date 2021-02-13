package com.fizz.buzz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FizzBuzzTest {

    private FizzBuzz sut;

    @BeforeEach
    public void init(){
        sut = new FizzBuzz();
    }

    @Test
    public void testConvertValue1() {
        String expected = "1";
        String actual = sut.map(1);
        Assertions.assertEquals(expected, actual);
    }
}
