package com.fizz.buzz.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;

@ToString
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class FizzBuzzInput {
    @Min(value = 1, message = "Start cannot be less than 1")
    private int startInclusive;
    @Min(value = 1, message = "End cannot be less than 1")
    private int endInclusive;
}
