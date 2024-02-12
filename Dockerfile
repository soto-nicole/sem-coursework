#Use the latest version of the OpenJDK base image
FROM openjdk:latest

# Copying the compiled JAR file from the local target directory to the /tmp directory inside the image
COPY ./target/sem-coursework-0.1.0.3-jar-with-dependencies.jar /tmp

#Setting the directory inside the Docker containter to temp
WORKDIR /tmp

# Entrypoint to the Java application packaged in the JAR file
ENTRYPOINT ["java", "-jar", "sem-coursework-0.1.0.3-jar-with-dependencies.jar"]