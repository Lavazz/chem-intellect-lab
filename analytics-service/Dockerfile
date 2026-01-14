FROM maven:3.9.6-amazoncorretto-21 AS build
WORKDIR /app
COPY . .
RUN mvn -B clean package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/analytics-service/target/*.jar app.jar
EXPOSE 9001
ENTRYPOINT ["sh", "-c", "java -jar app.jar"]