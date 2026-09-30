# Build stage
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
ENV MAVEN_OPTS="-Xmx256m"
RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/lms-0.0.1-SNAPSHOT.jar app.jar
ENV PORT=8080
EXPOSE $PORT
ENTRYPOINT ["sh", "-c", "java -Xmx256m -jar app.jar --server.port=${PORT}"]
