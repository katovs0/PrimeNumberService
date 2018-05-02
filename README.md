# PrimeNumberService

## To Pakcage
> mvn clean package

## To start
> java -jar target/prime-number-service-0.1.0.jar


## To call the servive
> curl localhost:6399/primes/100

> curl -v -H "Accept: application/xml" localhost:6399/primes/100?algorithm=slow

> curl -v -H "Accept: application/json" localhost:6399/primes/101?algorithm=bruteForce

> curl -v -H "Accept: application/json" localhost:6399/primes/102?algorithm=java8

> curl -v -H "Accept: application/json" localhost:6399/primes/103?algorithm=eratosthenesSieve
