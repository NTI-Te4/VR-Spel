FROM maven:latest AS build

WORKDIR /app

COPY Backend/pom.xml .
COPY Backend/src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:24-jdk-alpine

WORKDIR /app

COPY --from=build /app/target/*.jar ./VRServerApp.jar

EXPOSE 8001

CMD ["java", "-jar", "VRServerApp.jar"]
