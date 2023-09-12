# Use an official Java 8 runtime as a parent image
FROM openjdk:8-jre-alpine

# Set the working directory to /app
WORKDIR /app

# Copy the executable JAR file into the container at /app
COPY app.jar /app/

# Specify the command to run your application
CMD ["java", "-jar", "app.jar"]
