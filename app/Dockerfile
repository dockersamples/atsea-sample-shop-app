FROM node:latest AS storefront
WORKDIR /usr/src/atsea/app/react-app
COPY react-app .
RUN npm install
RUN npm run build

FROM maven:latest AS appserver
WORKDIR /usr/src/atsea
COPY pom.xml .
RUN mvn -B -f pom.xml -s /usr/share/maven/ref/settings-docker.xml dependency:resolve
COPY . .
RUN mvn -B -s /usr/share/maven/ref/settings-docker.xml package -DskipTests

FROM java:8-jdk-alpine
RUN adduser -Dh /home/gordon gordon
WORKDIR /static
COPY --from=storefront /usr/src/atsea/app/react-app/build/ .
WORKDIR /app
RUN apk update
RUN apk add ca-certificates wget
RUN wget -O dd-java-agent.jar 'https://repository.sonatype.org/service/local/artifact/maven/redirect?r=central-proxy&g=com.datadoghq&a=dd-java-agent&v=LATEST'
COPY --from=appserver /usr/src/atsea/target/AtSea-0.0.1-SNAPSHOT.jar .
# ENTRYPOINT ["java", "-jar", "/app/AtSea-0.0.1-SNAPSHOT.jar"]
ENTRYPOINT ["java", "-javaagent:/app/dd-java-agent.jar", "-Ddd.agent.host=datadog-agent","-Ddd.service.name=atsea", "-Ddd.agent.port=8126", "-Ddd.trace.analytics.enabled=true", "-jar", "/app/AtSea-0.0.1-SNAPSHOT.jar"]
CMD ["--spring.profiles.active=postgres"]


# ENTRYPOINT ["java", "-javaagent:/app/dd-java-agent.jar", "-Ddd.agent.host=datadog-agent", "-Ddd.agent.port=8126", "-Ddd.trace.analytics.enabled=true", "-jar", "/app/AtSea-0.0.1-SNAPSHOT.jar"]