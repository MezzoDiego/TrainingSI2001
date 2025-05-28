FROM maven:3.9.9-eclipse-temurin-21 as builder
WORKDIR /opt
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk
COPY --from=builder /opt/target/*.jar /opt/app.jar
EXPOSE 8080
CMD ["java", "-jar", "/opt/app.jar"]
