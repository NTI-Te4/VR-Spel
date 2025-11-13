FROM maven:latest AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:24-jdk-alpine

WORKDIR /app

COPY --from=build /app/target/*.jar ./VRServerApp.jar

EXPOSE 8001

CMD ["java", "-jar", "VRServerApp.jar"]
