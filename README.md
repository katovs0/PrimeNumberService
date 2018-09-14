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


# Future improvements
## Asynchronous method calls (ref:https://www.e4developer.com/2018/03/30/introduction-to-concurrency-in-spring-boot/)
    - How do you enable asynchronous method calls in Spring Boot? You want to start with @EnableAsync annotation on your Application class under the @SpringBootApplication annotation.
    - With that enabled, you can use @Async annotation in your services that return CompletableFuture<>. Because you have @EnableAsync , the @Async methods will be run in a background thread pool.
    - by default SimpleAsyncTaskExecutor is used for async method calls
## Message-driven with queues (JMS)
## Event-driven (messaging-reactor)

# Solr service:
## require Solr service setup as per: https://lucene.apache.org/solr/guide/7_3/solr-tutorial.html#create-a-new-collection
## start 2 nodes:
> ./bin/solr start -c -p 8983 -s example/cloud/node1/solr
>./bin/solr start -c -p 7574 -s example/cloud/node2/solr -z localhost:9983

## Start com.service.number.Application with no VM params:

## Invoke REST servce:
> curl -v -H "Accept: application/json" http://localhost:6399/solr/techproducts?q=cat:electronics&rows=100




Sping MVC
Jackson for json/xml binding (Java object - json/xml)
