# Use Maven with JDK 11 as the base image
FROM maven:3.6.3-jdk-11

# Set the working directory inside the container
WORKDIR /app

# Copy only the pom.xml initially to leverage Docker cache
COPY pom.xml ./

# Install dependencies in a separate layer to improve build performance
RUN mvn dependency:go-offline

# Copy the rest of the project files
COPY .. .

# Compile and package the application without running tests
RUN mvn clean install -DskipTests

# Command to run tests when the container starts
CMD ["mvn", "test", "-Dcucumber.options=${CUCUMBER_OPTIONS}"]

