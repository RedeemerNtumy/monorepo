# Step 1: Build Stage
FROM openjdk:21-jdk
WORKDIR /app
EXPOSE 1818
ENV SPRING_PROFILES_ACTIVE docker
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]
