FROM maven:latest AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:24-jdk-alpine

WORKDIR /app

COPY --from=build /app/target/skibiloader-1.0.0.jar ./skibiloader.jar

EXPOSE 8001

CMD ["java", "-jar", "skibiloader.jar"]
