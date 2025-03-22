package ru.hogwarts.school5.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.IntStream;
import java.util.stream.Stream;

@RestController
@RequestMapping("calculate")
public class CalculateController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CalculateController.class);

    @GetMapping
    public void calculate() {

        // option 1
        long startTime = System.currentTimeMillis();

        int sum = Stream.iterate(1, a -> a + 1)
                .limit(1_000_000)
                .reduce(0, (a, b) -> a + b);

        long endTime = System.currentTimeMillis();

        LOGGER.info("Option 1: {} ms", endTime - startTime);


        // option 2
        startTime = System.currentTimeMillis();

        int sum2 = IntStream.rangeClosed(1, 1_000_000).sum();

        endTime = System.currentTimeMillis();

        LOGGER.info("Option 2: {} ms", endTime - startTime);


        // option 3
        startTime = System.currentTimeMillis();

        int n = 1_000_000;
        int sum3 = n * (n + 1) / 2;

        endTime = System.currentTimeMillis();

        LOGGER.info("Option 3: {} ms", endTime - startTime);
    }


}
