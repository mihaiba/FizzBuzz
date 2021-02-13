package com.fizz.buzz.controller;

import com.fizz.buzz.model.FizzBuzzInput;
import com.fizz.buzz.service.FizzBuzz;
import com.google.common.collect.ImmutableList;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class FizzBuzzController {


    private final FizzBuzz service;

    @PostMapping("/fizzbuzz")
    public List<String> map(@RequestBody FizzBuzzInput input) {
        log.info("Requested FizzBuzz details with input {}", input);
        return ImmutableList.of();
    }
}
