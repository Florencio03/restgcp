FROM eclipse-temurin:21-jdk AS builder
WORKDIR /app

# Copy Maven wrapper and set exec permission
COPY mvnw .
COPY .mvn .mvn
RUN chmod +x mvnw

# Copy everything else
COPY . .

RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

