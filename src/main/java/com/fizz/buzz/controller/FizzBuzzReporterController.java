package com.fizz.buzz.controller;

import com.fizz.buzz.model.FizzBuzzInput;
import com.fizz.buzz.model.FizzBuzzReportOutput;
import com.fizz.buzz.service.FizzBuzzReporter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

@RestController
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class FizzBuzzReporterController {

    private final FizzBuzzReporter service;

    @PostMapping("/report")
    public FizzBuzzReportOutput map(@Valid @RequestBody FizzBuzzInput input) {
        log.info("Requested FizzBuzz details with input {}", input);

        List<Integer> request = IntStream.rangeClosed(input.getStartInclusive(), input.getEndInclusive())
                .boxed()
                .collect(toList());
        Map<String, Long> result = service.generateReport(request);
        return FizzBuzzReportOutput.builder()
                .result(result)
                .build();
    }
}
