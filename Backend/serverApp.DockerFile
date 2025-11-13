FROM openjdk:24-jdk
ARG JAR_FILE=server/target/*.jar

COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]