# Stage 1: Build stage
FROM eclipse-temurin:21-jdk-jammy AS builder

WORKDIR /app

# Copy Maven wrapper and pom.xml
COPY backend/product-service/.mvn .mvn
COPY backend/product-service/mvnw mvnw
COPY backend/product-service/pom.xml .

# Make mvnw executable
RUN chmod +x ./mvnw

# Copy source code
COPY backend/product-service/src src

# Build the application
RUN ./mvnw clean package -DskipTests

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

# Run the application
ENTRYPOINT ["java"]
CMD ["-jar", "app.jar"]
