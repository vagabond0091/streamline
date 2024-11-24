FROM openjdk:17

# Set the working directory
WORKDIR /streamline

# Copy the built JAR file into the container
COPY target/*.jar streamline.jar

# Run the application
ENTRYPOINT ["java", "-jar", "streamline.jar"]