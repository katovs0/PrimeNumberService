package com.service.number.prime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class PrimeCalculatorStrategyFactory {

    private final Map<PrimeCalculatorStrategyName, PrimeCalculatorService> primeCalculatorStrategies;

    @Autowired
    public PrimeCalculatorStrategyFactory(Set<PrimeCalculatorService> strategies) {
        primeCalculatorStrategies = new HashMap<>();
        strategies.forEach(this::registerStrategy);
    }

    /*
     * default strategy EratosthenesSievePrimeCalculatorStrategy
     */
    public PrimeCalculatorService getStrategy(String requestedStrategyName) {
        PrimeCalculatorStrategyName strategyName = PrimeCalculatorStrategyName.getOrDefault(requestedStrategyName);
        return primeCalculatorStrategies.get(strategyName);
    }

    private void registerStrategy(PrimeCalculatorService strategy) {
        primeCalculatorStrategies.put(strategy.getStrategyName(), strategy);
    }
}
