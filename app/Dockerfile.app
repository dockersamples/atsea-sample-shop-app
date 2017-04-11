FROM node:latest
COPY . /usr/src/atsea
WORKDIR /usr/src/atsea/app/react-app
RUN npm install
RUN npm run build

FROM maven:latest
COPY . /usr/src/atsea
WORKDIR /usr/src/atsea
RUN mvn package -DskipTests

FROM java:8-jdk-alpine
RUN mkdir -p /app/
COPY --from=0 /usr/src/atsea/app/react-app/build /app/static
COPY --from=1 /usr/src/atsea/target/AtSea-0.0.1-SNAPSHOT.jar /app
CMD ["java", "-jar","/app/AtSea-0.0.1-SNAPSHOT.jar", "--spring.profiles.active=prod", "--debug"]
