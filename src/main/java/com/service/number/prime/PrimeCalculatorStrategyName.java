package com.service.number.prime;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public enum PrimeCalculatorStrategyName {
    BruteForcePrimeCalculatorStrategy("bruteForce"),
    EratosthenesSievePrimeCalculatorStrategy("eratosthenesSieve"),
    Java8PrimeCalculatorStrategy("java8"),
    SlowPrimeCalculatorStrategy("slow");

    private final String algoName;

    private static final Map<String, PrimeCalculatorStrategyName> ENUM_MAP;

    PrimeCalculatorStrategyName(String algoName) {
        this.algoName = algoName;
    }

    String getAlgoName() {
        return algoName;
    }

    // Build an immutable map of String name to enum pairs.
    // Any Map impl can be used.

    static {
        Map<String, PrimeCalculatorStrategyName> map = new ConcurrentHashMap<>();
        for (PrimeCalculatorStrategyName instance : PrimeCalculatorStrategyName.values()) {
            map.put(instance.getAlgoName().toLowerCase(), instance);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }

    /*
     * default EratosthenesSievePrimeCalculatorStrategy
     */
    public static PrimeCalculatorStrategyName getOrDefault(String algoName) {
        return Optional.ofNullable(ENUM_MAP.get(algoName.toLowerCase())).orElse(EratosthenesSievePrimeCalculatorStrategy);
    }

}
