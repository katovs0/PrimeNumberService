package com.service.number.prime;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
class SlowPrimeCalculator implements PrimeCalculatorService {

    @Override
    public List<Integer> getPrimes(int maxNumber) {
        List<Integer> primes = new ArrayList<>();

        for (int currentNumber = 2; currentNumber <= maxNumber; currentNumber++) {
            int counter = 0;
            for (int i = currentNumber; i > 1; i--) {
                if (currentNumber % i == 0) {
                    counter++;
                }
            }
            if (counter == 1) {
                primes.add(currentNumber);
            }
        }

        return primes;
    }

    @Override
    public PrimeCalculatorStrategyName getStrategyName() {
        return PrimeCalculatorStrategyName.SlowPrimeCalculatorStrategy;
    }

}
