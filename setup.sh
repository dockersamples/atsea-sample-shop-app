#!/bin/bash  
docker-compose down
mkdir -p app/react-app/node_modules
npm install --prefix app/react-app
npm run build --prefix app/react-app
rm -r app/static; mv app/react-app/build app/static
mvn package
cp -f target/AtSea-0.0.1-SNAPSHOT.jar app/
docker-compose build
docker-compose up
