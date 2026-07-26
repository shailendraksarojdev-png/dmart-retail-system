FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

# Copy the built jar directly from your GitHub runner's target folder
COPY target/*.jar app.jar

# Expose port
EXPOSE 8080

# Set environment variables
ENV JAVA_OPTS="-Xmx512m -Xms256m"

# Run the application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
