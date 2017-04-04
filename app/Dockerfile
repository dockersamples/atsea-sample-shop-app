FROM java:8-jdk-alpine

RUN mkdir -p /app/ && \
    mkdir -p /static

COPY target/MobyArtShop-0.0.1-SNAPSHOT.jar /app

CMD ["java", "-jar","/app/MobyArtShop-0.0.1-SNAPSHOT.jar", "--spring.profiles.active=postgres", "--debug"]
