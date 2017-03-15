#!/bin/bash

cd ..
mvn package
cp target/MobyStore-0.0.1-SNAPSHOT.jar  app/
