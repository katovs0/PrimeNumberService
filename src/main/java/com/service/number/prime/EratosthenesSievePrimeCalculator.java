package com.service.number.prime;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Component
class EratosthenesSievePrimeCalculator implements PrimeCalculatorService {

    @Override
    public List<Integer> getPrimes(int maxNumber) {
        List<Integer> primeNumbers = new LinkedList<>();
        boolean[] primes = computeSieve(maxNumber);

        for (int i = 2; i <= maxNumber; i++) {
            if (primes[i]) {
                primeNumbers.add(i);
            }
        }
        return primeNumbers;
    }

    private static boolean[] computeSieve(int maxNumber) {
        boolean primes[] = new boolean[maxNumber + 1];
        Arrays.fill(primes, true);

        for (int p = 2; p * p <= maxNumber; p++) {
            if (primes[p]) {
                for (int i = p + p; i <= maxNumber; i += p) {
                    primes[i] = false;
                }
            }
        }
        return primes;
    }

}
