#!/usr/bin/env bash
#
# This scripts starts the SmartDataStoreService.
#

echo Starting SmartDataStoreService ...

# Starting the service
java -jar target/datastore-service.jar
