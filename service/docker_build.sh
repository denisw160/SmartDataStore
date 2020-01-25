#!/bin/bash
#
# Building the service and prepare the Docker images.
#
# User must have access to Docker.
#
# Usage: ./build.sh
# 

NAME=smartdatastore-service
TAG=latest

# Remove unused images
#echo Remove unused images
#docker image prune -a -f

# Building the image
echo Building the image
docker build -t $NAME:$TAG .

# Running the container
#echo Running the container on port 8889
#docker run --rm -it --name smartdatastore-service-p 8889:8080 --link smartdatastore-mongodb:db --link smartdatastore-mosquitto:mqtt $NAME:$TAG

## need database
#docker run -d --name smartdatastore-mongodb -v smartdatastore-mongodb-data:/data/db mongo:latest

## need mosquitto mqtt broker
#docker run -d --name smartdatastore-mosquitto -p 1883:1883 -v smartdatastore-mosquitto-conf:/mosquitto/config -v smartdatastore-mosquitto-data:/mosquitto/data -v smartdatastore-mosquitto-log:/mosquitto/log eclipse-mosquitto:latest
