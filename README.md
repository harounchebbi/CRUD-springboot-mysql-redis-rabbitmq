# CRUD-springboot-mysql-redis-rabbitmq

This is a modern spring boot CRUD application that has a fully functional & secured user management system and it uses rabbitmq for event-driven updates, redis for caching and mysql as a database. 

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

To build and run the application you need : 

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)
- [RabbitMQ](https://www.rabbitmq.com/)
- [Redis](https://redis.io/)
- [MySQL](https://www.mysql.com/)

### Running the app locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.app.crud.DemoApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

## MySQL

1. Create a database in your MySQL instance.
2. Update the application.yaml file in the `src/main/resources` folder with the URL, username and password for your MySQL instance. The table schema will be created for you in the database.

## RabbitMQ
Real time event updates are managed in RabbitMQ. Two scenarios here : 

```shell
# Updating the list when a user is created
# Updating the list when a user is deleted
```

## Redis

We are using Redis as our caching system. The configuration in application.yaml remains the same. 

## Swagger
We used Swagger to easily generate REST API documentation for our project. 

### Endpoints

|Method | 	Url		| 	Description |
|-------| ------- | ----------- |
|GET| 	/info	| 	info / heartbeat - provided by boot |
|GET| /health |	application health - provided by boot|
|GET| /v2/api-docs| 	swagger json|
|GET|/swagger-ui.html| 	swagger html|
|GET|/api/users/{id}| 	get user by id|
|GET|/api/users| 	get N users with an offset|
|POST|/api/users| create user|
|PUT|/api/users/{id}| update user|
|DELETE|/api/users/{id}| delete user|
|GET|/api/users/me| current user|
|POST|/auth/signin| authenticate user|
