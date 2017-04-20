FROM node:latest
WORKDIR /usr/src/atsea/app/react-app
COPY app/react-app/package.json .
RUN npm install
COPY . /usr/src/atsea
RUN npm run build

FROM maven:latest
WORKDIR /usr/src/atsea
COPY pom.xml .
RUN mvn -B -f pom.xml -s /usr/share/maven/ref/settings-docker.xml dependency:resolve
COPY . .
RUN mvn -B -s /usr/share/maven/ref/settings-docker.xml package -DskipTests

FROM java:8-jdk-alpine
WORKDIR /static
COPY --from=0 /usr/src/atsea/app/react-app/build/ .
WORKDIR /app
COPY --from=1 /usr/src/atsea/target/AtSea-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "/app/AtSea-0.0.1-SNAPSHOT.jar"]
CMD ["--spring.profiles.active=postgres", "--debug"]
