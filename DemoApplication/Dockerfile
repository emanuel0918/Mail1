FROM openjdk:8-jre-alpine

# Create directories
RUN mkdir -p /opt/app /opt/app/images

# Copy the JAR file
COPY target/DemoApplication-0.0.1-SNAPSHOT.jar /opt/app/DemoApplication.jar

# Copy credentials.json
COPY src/main/resources/credentials.json /opt/app/credentials.json

# Set the working directory
WORKDIR /opt/app

# Expose the port
EXPOSE 8080

# Define the volume
VOLUME ["/opt/app/"]

# Run the application
CMD ["java", "-jar", "DemoApplication.jar"]
