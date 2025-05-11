FROM maven:3.8.3-openjdk-17 AS builder

WORKDIR /project

COPY . /project

RUN mvn clean install -DskipTests=true

FROM openjdk:17-alpine

WORKDIR /app

COPY --from=builder /project/target/*.war /app/my-project.war

CMD ["java", "-jar", "my-project.war"]

