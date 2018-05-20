package com.katovs.service.prime;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
class Java8PrimeCalculator implements PrimeCalculatorService {

    @Override
    public List<Integer> getPrimes(int maxNumber) {
        return IntStream.rangeClosed(2, maxNumber)
                .filter(Java8PrimeCalculator::isPrimeJava8).boxed()
                .collect(Collectors.toList());
    }

    private static boolean isPrimeJava8(int number) {
        return number >= 2 && IntStream.rangeClosed(2, (int) Math.sqrt(number)).noneMatch(i -> number % i == 0);
    }


}
