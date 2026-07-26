# Stage 1: Build stage
FROM eclipse-temurin:21-jdk-jammy AS builder

WORKDIR /app

# Copy the entire product-service to preserve project layout
COPY backend/product-service /app

# Ensure mvnw is executable
RUN chmod +x ./mvnw

# Build the application using the wrapper (batch mode)
RUN ./mvnw -f pom.xml clean package -DskipTests -B

# Stage 2: Runtime stage
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

# Copy the built jar from builder stage
COPY --from=builder /app/target/*.jar app.jar

# Expose port
EXPOSE 8080

# Set environment variables
ENV JAVA_OPTS="-Xmx512m -Xms256m"

# Health check
HEALTHCHECK --interval=30s --timeout=10s --start-period=40s --retries=3 \
  CMD java -cp app.jar org.springframework.boot.loader.JarLauncher --version || exit 1

# Run the application with JAVA_OPTS
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
