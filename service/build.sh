#!/usr/bin/env bash
#
# Building the service with maven
#

echo Building SmartDataStoreService ...
./mvnw.sh -DskipTests clean package
