package com.service.number.prime

import org.junit.Test

class PrimeCalculatorStrategyNameTest {

    @Test
    public void testGetByAlgoName() {

        assert PrimeCalculatorStrategyName.BruteForcePrimeCalculatorStrategy == PrimeCalculatorStrategyName.getOrDefault("bruteForce")
        assert PrimeCalculatorStrategyName.EratosthenesSievePrimeCalculatorStrategy == PrimeCalculatorStrategyName.getOrDefault("eratosthenesSieve")
        assert PrimeCalculatorStrategyName.Java8PrimeCalculatorStrategy == PrimeCalculatorStrategyName.getOrDefault("java8")
        assert PrimeCalculatorStrategyName.SlowPrimeCalculatorStrategy == PrimeCalculatorStrategyName.getOrDefault("slow")
    }

    @Test
    public void testGetOrDefault() {

        assert PrimeCalculatorStrategyName.EratosthenesSievePrimeCalculatorStrategy == PrimeCalculatorStrategyName.getOrDefault("")
    }
}
