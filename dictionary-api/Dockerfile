FROM openjdk:8-jdk

#RUN apt-get update && apt-get install -y --no-install-recommends openjfx && rm -rf /var/lib/apt/lists/*

ARG AWS_ACCESS_KEY
ARG AWS_SECRET_KEY

ENV AWS_ACCESS_KEY $AWS_ACCESS_KEY
ENV AWS_SECRET_KEY $AWS_SECRET_KEY

VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/dictionary-api-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar","-Dspring.profiles.active=prod","/app.jar"]
