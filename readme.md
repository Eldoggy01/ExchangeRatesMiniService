# ExchangeRatesMiniService


## Running ExchangeRatesMiniService locally from Gradle
ExchangeRatesMiniService is a [Spring Boot](https://spring.io/guides/gs/spring-boot) application. You can run it with 
following gradle command from project directory:


```
./gradlew clean test bootRun

```
## Running ExchangeRatesMiniService in Docker container:
The following command builds docker image, starts container, and runs our project inside container. The command should be run from the project directory:

```
docker-compose up --build

```

## ExchangeRatesMiniService endpoints:

Base url is:  http://localhost:9090/

ExchangeRatesMiniService has only one endpoint:
GET http://localhost:9090/api/current-day-success/{currencyCode} 
- currencyCode is PathVariable, which is a code of currency in uppercase.
 For example:  EUR, USD, RUB, BTC.

The method checks whether the latest exchange rate of 'currencyCode' against the ruble is smaller than it was yesterday.

If it's smaller than it was yesterday, then service response body contains url to gif from https://giphy.com/search/broke,
else it responses with response body contains url to gif from https://giphy.com/search/rich

