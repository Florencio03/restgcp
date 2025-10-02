FROM eclipse-temurin:21-jdk AS builder
WORKDIR /app

# Copy the wrapper and give permissions first
COPY mvnw .
COPY .mvn .mvn
RUN chmod +x mvnw

# Copy the rest of the source AFTER fixing mvnw
COPY . .

RUN ./mvnw clean package -DskipTests


FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
