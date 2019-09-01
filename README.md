# SmartDataStore

**State**: Still in development

An extendable data store for accelerating small IoT projects.


## Components

**Web-Frontend**

The web frontend provides a simple managent UI for the monitoring and administration of the SmartDataStore.

**Backend-Service**

This is backend service for processing the incoming and outgoing data. The SmartDataStore is suitable for small IoT projects. The service provides an API for incoming messages from IoT devices. For accessing the data it provides a REST API and an OData interface. For managing the service an optional web front end is available. The project also provides an extensible plugin system for customized interfaces. The data is stored in the MongoDB.
