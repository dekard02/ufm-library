FROM eclipse-temurin:11

WORKDIR /app

COPY .mvn .mvn
COPY mvnw pom.xml ./


COPY ./src ./src
COPY ./public ./public

RUN ./mvnw dependency:go-offline

CMD ["./mvnw", "spring-boot:run"]