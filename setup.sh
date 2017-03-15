#!/bin/bash  
docker-compose down
~/apache-maven-3.3.9/bin/mvn package
cp -f target/MobyStore-0.0.1-SNAPSHOT.jar app/
docker-compose build
docker-compose up
