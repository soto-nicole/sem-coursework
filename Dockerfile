#Use the latest version of the OpenJDK base image
FROM openjdk:latest
COPY ./target/sem-coursework.jar /tmp
#Setting the directory inside the Docker containter to temp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "sem-coursework.jar", "db:3306", "30000"]