FROM java:8-jdk-alpine

RUN mkdir -p /app/

COPY MobyStore-0.0.1-SNAPSHOT.jar /app

CMD ["java", "-jar","/app/MobyStore-0.0.1-SNAPSHOT.jar", "--spring.profiles.active=prod", "--debug"]

