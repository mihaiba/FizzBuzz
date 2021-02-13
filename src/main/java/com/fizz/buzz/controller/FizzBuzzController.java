package com.fizz.buzz.controller;

import com.fizz.buzz.model.FizzBuzzInput;
import com.fizz.buzz.model.FizzBuzzOutput;
import com.fizz.buzz.service.FizzBuzz;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

@RestController
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class FizzBuzzController {

    private final FizzBuzz service;

    @PostMapping("/fizzbuzz")
    public FizzBuzzOutput map(@Valid @RequestBody FizzBuzzInput input) {
        log.info("Requested FizzBuzz details with input {}", input);

        List<String> result = IntStream.rangeClosed(input.getStartInclusive(), input.getEndInclusive())
                .boxed()
                .map(service::map)
                .collect(toList());
        return FizzBuzzOutput.builder()
                .result(result)
                .build();
    }
}
