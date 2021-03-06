FROM adoptopenjdk/openjdk11:alpine-jre
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

EXPOSE 9005
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=test","/app.jar"]

