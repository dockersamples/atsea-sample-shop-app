FROM maven:latest
COPY . /usr/src/mobystore
WORKDIR /usr/src/mobystore
RUN mvn package

FROM java:8-jdk-alpine
RUN mkdir -p /app/
COPY --from=0 /usr/src/mobystore/target/MobyStore-0.0.1-SNAPSHOT.jar /app
CMD ["java", "-jar","/app/MobyStore-0.0.1-SNAPSHOT.jar", "--spring.profiles.active=prod", "--debug"]