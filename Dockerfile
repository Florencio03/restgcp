FROM eclipse-temurin:21-jdk AS builder
WORKDIR /app

# Copy Maven wrapper first
COPY mvnw .
COPY .mvn .mvn
RUN chmod +x mvnw

# Copy rest of the project
COPY pom.xml .
COPY src src

RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
