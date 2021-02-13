package com.fizz.buzz;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;

@AllArgsConstructor
public class FizzBuzzReporter {
    private static final String AN_INTEGER_KEY = "an integer";

    private final FizzBuzz service;

    public Map<String, Long> generateReport(List<Integer> input) {
        return input.stream()
                .map(service::map)
                .collect(Collectors.groupingBy(this::mapToKey, counting()));
    }

    private String mapToKey(String input) {
        return isNumber(input) ? AN_INTEGER_KEY : input;
    }

    private boolean isNumber(String input) {
        if (input == null) {
            return false;
        }
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
