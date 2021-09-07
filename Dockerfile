FROM gradle:jdk11

WORKDIR /app

COPY . .

CMD ./gradlew clean test bootRun