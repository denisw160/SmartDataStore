## SmartDataStoreService

### Description
This is backend service for processing the incoming and outgoing data. The SmartDataStore is suitable for small IoT
projects. The service provides an API for incoming messages from IoT devices. For accessing the data it provides a 
REST API and an OData interface. For managing the service an optional web front end is available. The project also 
provides an extensible plugin system for customized interfaces. The data is stored in the MongoDB.

### Scripts
The start script can be used to start the server on the local console.

    bash>start.sh

The run script can be used to build and start the server on the local console.

    bash>run.sh

For clean and building the service the following scripts can be used.

    bash>clean.sh
    bash>build.sh

To start the Docker build, execute this script

    bash>docker_build.sh
