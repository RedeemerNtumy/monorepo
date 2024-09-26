FROM maven:3.6.3-jdk-11

# Set the working directory
WORKDIR /app

# Copy the project files to the container
COPY . .

# Install dependencies and run the tests
RUN mvn clean install

CMD ["mvn", "test"]