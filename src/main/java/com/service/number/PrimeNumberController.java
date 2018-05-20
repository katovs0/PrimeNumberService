package com.service.number;

import com.service.number.domain.PrimesResponse;
import com.service.number.exception.IllegalAlgorithmException;
import com.service.number.prime.PrimeCalculatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PrimeNumberController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ApplicationContext context;

    @GetMapping("/")
    public String index() {
        return "Welcome to the Prime Number Service!";
    }

    @Cacheable(value = "primes", key = "#maxNumber", sync = true)
    @GetMapping(value = "/primes/{maxNumber}",
            produces = {"application/json", "application/xml"})
    public PrimesResponse getPrimes(@PathVariable int maxNumber,
                                    @RequestParam(name = "algorithm",
                                            required = false,
                                            defaultValue = "eratosthenesSieve") String algorithm) {
        logger.info("Start retrieving list of prime numbers until {} [algorithm={}]", maxNumber, algorithm);
        List result;
        long start = System.currentTimeMillis();

        try {
            result = context.getBean(algorithm + "PrimeCalculator", PrimeCalculatorService.class).getPrimes(maxNumber);
        } catch (NoSuchBeanDefinitionException e) {
            logger.warn("Invalid algorithm requested [{}] ", algorithm);
            throw new IllegalAlgorithmException(algorithm);
        }

        logger.info("Finished retrieving list of prime numbers until {} [algorithm={}] took {} ms.",
                maxNumber,
                algorithm,
                System.currentTimeMillis() - start);

        return new PrimesResponse(maxNumber, result);
    }

    @GetMapping(value = "/primes/",
            produces = {"application/json", "application/xml"})
    public PrimesResponse getPrimes() {
        return getPrimes(10, "eratosthenesSieve");
    }

}