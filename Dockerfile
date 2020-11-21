FROM adoptopenjdk/openjdk11:ubi
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
# 80 포트 오픈
EXPOSE 9010
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","/app.jar"]

