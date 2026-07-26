# Stage 1: Build stage
FROM eclipse-temurin:21-jdk-jammy AS builder

WORKDIR /app

# Copy parent POM and backend directory to preserve project structure
COPY pom.xml ./pom.xml
COPY backend ./backend

# Make mvnw executable in product-service
RUN chmod +x ./backend/product-service/mvnw

# Build the entire project from root using parent pom
# This builds all modules and product-service jar will be in backend/product-service/target/
RUN ./backend/product-service/mvnw -B clean package -DskipTests

# Stage 2: Runtime stage
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

# Copy the built jar from builder stage
COPY --from=builder /app/backend/product-service/target/*.jar app.jar

# Expose port
EXPOSE 8080

# Set environment variables
ENV JAVA_OPTS="-Xmx512m -Xms256m"

# Health check
HEALTHCHECK --interval=30s --timeout=10s --start-period=40s --retries=3 \
  CMD java -cp app.jar org.springframework.boot.loader.JarLauncher --version || exit 1

# Run the application with JAVA_OPTS
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
