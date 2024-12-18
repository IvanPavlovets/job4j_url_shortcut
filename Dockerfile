# Stage 1 - jar packaging
FROM maven:3.8-openjdk-17 AS maven
WORKDIR /app
COPY . /app
RUN mvn -DskipTests=true install

# Stage 2 - project running
FROM openjdk:17.0.2-jdk
WORKDIR /app
COPY --from=maven /app/target/job4j_url_shortcut-0.0.1-SNAPSHOT.jar app.jar
CMD java -jar app.jar