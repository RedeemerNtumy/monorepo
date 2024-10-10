# Step 1: Build Stage
FROM maven:3.8.5-openjdk-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Step 2: Run Stage
FROM openjdk:21-jdk-slim
VOLUME /tmp
EXPOSE 8989
ENV SPRING_PROFILES_ACTIVE docker
ENV SPRING_DATASOURCE_URL jdbc:postgresql://db:5432/program-creation
ENV SPRING_DATASOURCE_USERNAME postgres
ENV SPRING_DATASOURCE_PASSWORD postgres
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
