# Step 1: Build Stage
FROM openjdk:21-jdk
WORKDIR /app
EXPOSE 8989
ENV SPRING_PROFILES_ACTIVE docker
ENV SPRING_DATASOURCE_URL jdbc:postgresql://db:5432/user-service
ENV SPRING_DATASOURCE_USERNAME postgres
ENV SPRING_DATASOURCE_PASSWORD postgres
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]
