package com.fizz.buzz;

import com.google.common.collect.ImmutableMap;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class FizzBuzzReporter {

    private final FizzBuzz service;

    public Map<String, Long> generateReport(List<Integer> input) {
        return ImmutableMap.of();
    }
}
