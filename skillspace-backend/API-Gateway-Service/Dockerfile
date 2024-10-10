FROM openjdk:21-jdk
WORKDIR /app
EXPOSE 8080
ENV SPRING_PROFILES_ACTIVE docker
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]