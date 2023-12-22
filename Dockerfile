FROM openjdk:17-jdk-slim

# Install dependencies (you can adjust this based on your requirements)
RUN apt-get update && \
    apt-get install -y maven && \
    rm -rf /var/lib/apt/lists/*

# Set working directory
WORKDIR /app
# Copy and run your application
COPY . .
CMD ["java", "-jar", "authentication-0.0.1-SNAPSHOT.jar"]
