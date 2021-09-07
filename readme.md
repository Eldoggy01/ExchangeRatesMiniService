# ExchangeRatesMiniService

## Service which show currency rate daily trends and response with gifs.

## Running ExchangeRatesMiniService locally from Gradle
ExchangeRatesMiniService is a [Spring Boot](https://spring.io/guides/gs/spring-boot) application. You can run it with 
following gradle command from project directory:


```
./gradlew clean test bootRun

```
## Running ExchangeRatesMiniService in Docker container(from project dir):
The following command builds docker image, starts container, and runs our project inside container.

```
docker-compose up --build

```

## ExchangeRatesMiniService endpoints:

Base url is:  http://localhost:9090/

ExchangeRatesMiniService has only one endpoint:
GET http://localhost:9090/api/current-day-success/{currencyCode} - currencyCode is PathVariable,
which is a code of currency in uppercase. For example:  EUR, USD, RUB, BTC.
The method checks whether the exchange rate of 'currencyCode' against the ruble is smaller than it was yesterday.
If its now smaller than yesterday, then service response bode contains url to gif from https://giphy.com/search/broke,
else it responses then service response body contains url to gif from https://giphy.com/search/rich

