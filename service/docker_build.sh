#!/bin/bash
#
# Building the service and prepare the Docker images.
#
# User must have access to Docker.
#
# Usage: ./build.sh
# 

NAME=datastore-service
TAG=latest

# Remove unused images
#echo Remove unused images
#docker image prune -a -f

# Building the image
echo Building the image
docker build -t $NAME:$TAG .

# Running the container
#echo Running the container on port 8889
#docker run --rm -it --name datastore-service-p 8889:8080 --link datastore-mongodb:db --link datastore-mosquitto:mqtt $NAME:$TAG

## need database
#docker run -d --name datastore-mongodb -v datastore-mongodb-data:/data/db mongo:latest

## need mosquitto mqtt broker
#docker run -d --name datastore-mosquitto -p 1883:1883 -v datastore-mosquitto-conf:/mosquitto/config -v datastore-mosquitto-data:/mosquitto/data -v datastore-mosquitto-log:/mosquitto/log eclipse-mosquitto:latest
