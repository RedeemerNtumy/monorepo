FROM openjdk:21-jdk
WORKDIR /app
EXPOSE 9898
ENV SPRING_PROFILES_ACTIVE docker
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]