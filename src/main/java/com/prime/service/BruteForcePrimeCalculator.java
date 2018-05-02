package com.prime.service;

import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
class BruteForcePrimeCalculator implements PrimeCalculatorService {

    @Override
    public List<Integer> getPrimes(int maxNumber) {
        List<Integer> primes = new LinkedList<>();
        for (int currentNumber = 2; currentNumber <= maxNumber; currentNumber++) {
            if (isPrimeBruteForce(currentNumber)) {
                primes.add(currentNumber);
            }
        }
        return primes;
    }

    private static boolean isPrimeBruteForce(int number) {
        if (number < 2) {
            return false;
        }
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

}
