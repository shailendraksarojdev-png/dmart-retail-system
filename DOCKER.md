# Docker Setup and Commands Guide

This document contains all the commands needed to build and run the Dmart Retail System using Docker.

---

## 📋 Prerequisites

- Docker installed ([Download Docker](https://www.docker.com/products/docker-desktop))
- Docker version 20.10+ recommended
- Git (for cloning the repository)

---

## 🔨 Building the Docker Image

Navigate to the project root directory (`dmart-retail-system`) before running these commands:

```bash
# Build the Docker image with latest tag
docker build -t dmart-product-service:latest .

# Build with a specific version tag
docker build -t dmart-product-service:1.0 .

# Build with build progress output
docker build -t dmart-product-service:latest . --progress=plain

# Build without cache (forces fresh build)
docker build -t dmart-product-service:latest . --no-cache
```

---

## 🚀 Running the Docker Container

### Option 1: Run in Foreground (See Logs)
```bash
docker run -p 8080:8080 dmart-product-service:latest
```

### Option 2: Run in Background (Detached Mode)
```bash
docker run -d \
  --name dmart-api \
  -p 8080:8080 \
  dmart-product-service:latest
```

### Option 3: Run with PostgreSQL Configuration
```bash
docker run -d \
  --name dmart-api \
  -p 8080:8080 \
  -e SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/dmart \
  -e SPRING_DATASOURCE_USERNAME=postgres \
  -e SPRING_DATASOURCE_PASSWORD=your_password \
  -e SPRING_JPA_HIBERNATE_DDL_AUTO=update \
  dmart-product-service:latest
```

### Option 4: Run with Memory Limits
```bash
docker run -d \
  --name dmart-api \
  -p 8080:8080 \
  -m 1024m \
  --cpus="1.0" \
  -e SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/dmart \
  -e SPRING_DATASOURCE_USERNAME=postgres \
  -e SPRING_DATASOURCE_PASSWORD=your_password \
  dmart-product-service:latest
```

### Option 5: Run with Custom Java Options
```bash
docker run -d \
  --name dmart-api \
  -p 8080:8080 \
  -e JAVA_OPTS="-Xmx1024m -Xms512m -XX:+UseG1GC" \
  -e SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/dmart \
  -e SPRING_DATASOURCE_USERNAME=postgres \
  -e SPRING_DATASOURCE_PASSWORD=your_password \
  dmart-product-service:latest
```

---

## 🔍 Container Management Commands

### Check Running Containers
```bash
# List running containers
docker ps

# List all containers (including stopped ones)
docker ps -a

# List containers with full command
docker ps -a --no-trunc
```

### View Container Logs
```bash
# View recent logs
docker logs dmart-api

# View last 50 lines
docker logs --tail 50 dmart-api

# Follow logs in real-time
docker logs -f dmart-api

# View logs with timestamps
docker logs -f --timestamps dmart-api
```

### Inspect Container
```bash
# Get detailed container information
docker inspect dmart-api

# Get container IP address
docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' dmart-api

# Get container status
docker inspect -f '{{.State.Status}}' dmart-api
```

### Execute Commands Inside Container
```bash
# Open interactive bash shell
docker exec -it dmart-api /bin/bash

# Run a specific command
docker exec dmart-api java -version

# Check disk usage inside container
docker exec dmart-api df -h
```

---

## ⏹️ Stopping and Removing Containers

### Stop Container
```bash
# Stop running container
docker stop dmart-api

# Stop container with timeout (waits 30 seconds before force kill)
docker stop -t 30 dmart-api

# Kill container immediately
docker kill dmart-api
```

### Remove Container
```bash
# Remove stopped container
docker rm dmart-api

# Force remove running container
docker rm -f dmart-api

# Remove all stopped containers
docker container prune

# Remove all stopped containers (with confirmation)
docker container prune -f
```

---

## 🖼️ Docker Image Management

### View Images
```bash
# List all images
docker images

# List images with detailed info
docker images -a

# Search for images
docker images | grep dmart
```

### Remove Images
```bash
# Remove image
docker rmi dmart-product-service:latest

# Remove image by ID
docker rmi <image_id>

# Force remove image
docker rmi -f dmart-product-service:latest

# Remove all unused images
docker image prune

# Remove all unused images (with confirmation)
docker image prune -a -f
```

### Tag Images
```bash
# Create a new tag
docker tag dmart-product-service:latest dmart-product-service:1.0

# Tag with registry
docker tag dmart-product-service:latest myregistry.azurecr.io/dmart-product-service:latest
```

---

## 🐳 Docker Compose (Recommended for Complete Setup)

The `docker-compose.yml` file has been created in the project root. It includes:
- PostgreSQL 15 Alpine (lightweight database)
- Spring Boot product-service
- Automatic health checks
- Network connectivity between services

### Docker Compose Commands

```bash
# Start all services
docker-compose up

# Start in background
docker-compose up -d

# Build and start services
docker-compose up -d --build

# View running services
docker-compose ps

# View logs
docker-compose logs

# Follow service logs
docker-compose logs -f

# Follow specific service logs
docker-compose logs -f product-service

# Stop services
docker-compose stop

# Stop and remove containers
docker-compose down

# Remove containers and volumes
docker-compose down -v

# Restart services
docker-compose restart

# Restart specific service
docker-compose restart product-service

# Execute command in service
docker-compose exec product-service bash

# Execute command in database
docker-compose exec postgres psql -U postgres -d dmart
```

---

## 🧹 Cleanup Commands

### Remove Unused Resources
```bash
# Remove stopped containers
docker container prune

# Remove dangling images
docker image prune

# Remove unused volumes
docker volume prune

# Full cleanup (containers, images, volumes, networks)
docker system prune

# Full cleanup with all unused resources
docker system prune -a --volumes
```

### View Disk Usage
```bash
# See Docker disk usage
docker system df

# See detailed disk usage
docker system df -v
```

---

## 🐛 Troubleshooting

### Error: "Connection to localhost:5432 refused"

**Problem:** The Docker container cannot connect to PostgreSQL

**Reason:** Inside a Docker container, `localhost` refers to the container itself, NOT the host machine.

**Solution 1: Use Docker Compose (RECOMMENDED)**
```bash
# Build and start all services together
docker-compose up -d

# Check if both services are running
docker-compose ps

# View logs
docker-compose logs -f product-service
```

**Solution 2: Use Docker Network**
```bash
# Create a network
docker network create dmart-net

# Start PostgreSQL
docker run -d \
  --name dmart-db \
  --network dmart-net \
  -e POSTGRES_DB=dmart \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=password \
  -p 5432:5432 \
  postgres:15-alpine

# Start the app (connect via hostname 'dmart-db')
docker run -d \
  --name dmart-api \
  --network dmart-net \
  -p 8080:8080 \
  -e SPRING_DATASOURCE_URL=jdbc:postgresql://dmart-db:5432/dmart \
  -e SPRING_DATASOURCE_USERNAME=postgres \
  -e SPRING_DATASOURCE_PASSWORD=password \
  dmart-product-service:latest
```

**Verify PostgreSQL is Running:**
```bash
docker ps | grep postgres
docker exec -it dmart-db psql -U postgres -d dmart
```

---

### Port Already in Use
```bash
# Find process using port 8080
lsof -i :8080

# Kill process using port 8080
kill -9 <PID>

# On Windows, use:
netstat -ano | findstr :8080
taskkill /PID <PID> /F
```

### View Container Resource Usage
```bash
# Real-time container stats
docker stats

# Specific container stats
docker stats dmart-api

# Get container memory usage
docker inspect dmart-api --format='{{.State.Pid}}'
```

### Network Issues
```bash
# Inspect network
docker network ls

# Inspect specific network
docker network inspect bridge

# Connect container to network
docker network connect <network> <container>

# Disconnect container from network
docker network disconnect <network> <container>
```

### Build Issues
```bash
# Build with verbose output
docker build -t dmart-product-service:latest . -v

# Build with no cache
docker build -t dmart-product-service:latest . --no-cache

# Check Dockerfile syntax
docker build -t dmart-product-service:latest . --dry-run
```

---

## 📊 Monitoring

### Container Metrics
```bash
# Live stats dashboard
docker stats

# Memory usage of specific container
docker stats --no-stream dmart-api

# All containers memory usage
docker stats --all
```

### Event Logging
```bash
# Real-time Docker events
docker events

# Events for specific container
docker events --filter 'container=dmart-api'

# Events in specific time range
docker events --since 10m
```

---

## 🔐 Security Best Practices

### Run with Read-Only Filesystem
```bash
docker run -d \
  --name dmart-api \
  -p 8080:8080 \
  --read-only \
  --tmpfs /tmp \
  dmart-product-service:latest
```

### Run with Limited Capabilities
```bash
docker run -d \
  --name dmart-api \
  -p 8080:8080 \
  --cap-drop=ALL \
  --cap-add=NET_BIND_SERVICE \
  dmart-product-service:latest
```

### Run with Non-Root User
```bash
# Build Dockerfile with USER directive
# Add to Dockerfile: USER appuser
docker run -d \
  --name dmart-api \
  -p 8080:8080 \
  --user 1000:1000 \
  dmart-product-service:latest
```

---

## 📝 Quick Reference Checklist

- [ ] Docker installed and running
- [ ] Navigate to project root: `cd dmart-retail-system`
- [ ] Build image: `docker build -t dmart-product-service:latest .`
- [ ] Run container: `docker run -d -p 8080:8080 dmart-product-service:latest`
- [ ] Check logs: `docker logs -f dmart-api`
- [ ] Access app: `http://localhost:8080`
- [ ] Stop container: `docker stop dmart-api`

---

## 📚 Additional Resources

- [Docker Documentation](https://docs.docker.com/)
- [Docker Compose Documentation](https://docs.docker.com/compose/)
- [Spring Boot Docker Guide](https://spring.io/guides/gs/spring-boot-docker/)
- [Best Practices for Java Docker Images](https://www.docker.com/blog/best-practices-for-java-docker/)

---

**Last Updated:** 2026-07-26
**Project:** Dmart Retail System
