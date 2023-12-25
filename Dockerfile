# Use a base image with Java and Maven installed
FROM openjdk:17

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file built from your Spring Boot application to the container
COPY target/*.jar /app/app.jar

# Expose the port your Spring Boot app runs on (assuming it's 8080)
EXPOSE 8080

# Command to run the Spring Boot application when the container starts
CMD ["java", "-jar", "app.jar"]
