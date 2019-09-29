# Transaction Service
Transaction Service exposes RESTful APIs for listing transactions.

### Running Transaction Service
#### Run and Test as standalone application 

Prerequisite:
- Java 12
- Maven 3

To execute unit test
```
mvnw clean test
```

To run the application
```
mvnw spring-boot:run
```

#### Run the application with docker
To build docker image
```
docker build -t american-orange-cadillac-transactions:0.1 .
``` 

To run the application in docker image
```
docker run -p 9091:9091 american-orange-cadillac-transactions:0.1 
```

### REST API Endpoints
The API documentation is generated with swagger and exposed at the endpoint:

http://[host]:9091/swagger-ui.html

![](docs/images/transaction_api.png)

### CI/CD with Jenkins

Transaction Service application is integrated with Jenkins to automate the CI/CD process.

![](docs/images/transaction_ci.png)