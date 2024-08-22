# Start with a base image containing Java runtime
FROM openjdk:17-jdk-slim

# Add Maintainer Info
LABEL maintainer="test_email@example.com"

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 and 8443 available to the world outside this container
EXPOSE 8080
EXPOSE 8443

# The application's jar file
ARG JAR_FILE=target/*.jar

# Add the application's jar to the container
ADD ${JAR_FILE} /app.jar

# Run the jar file
ENTRYPOINT ["java","-jar","/app.jar"]
