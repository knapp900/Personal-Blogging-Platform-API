# Personal Blogging Platform API

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)
![JUnit](https://img.shields.io/badge/JUnit-25A162?style=for-the-badge&logo=junit5&logoColor=white)

Welcome to the Personal Blogging Platform API! This project is a RESTful API for a blogging platform, allowing users to create, read, update, and delete blog posts, as well as manage user registrations and tags.

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
    - [Running the Application](#running-the-application)
    - [Running Tests](#running-tests)
- [API Documentation](#api-documentation)
- [Contact](#contact)

## Features

- User registration and authentication
- CRUD operations for blog posts
- Tag management for categorizing posts
- Search functionality by tags and keywords
- Pagination support for blog posts

## Technologies Used

- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **Hibernate**
- **Maven**
- **JUnit 5**
- **Mockito**
- **Docker**
- **Swagger/OpenAPI (SpringDoc)**

## Getting Started

### Prerequisites

- Java 17
- Maven
- Docker

### Installation

1. **Clone the repository:**

    ```sh
    git clone https://github.com/yourusername/personal-blogging-platform-api.git
    cd personal-blogging-platform-api
    ```

2. **Run PostgreSQL in Docker:**

    ```sh
    docker run --name blog-db -p 5432:5432 -e POSTGRES_USER=blog -e POSTGRES_PASSWORD=password -e POSTGRES_DB=blog postgres:16
    ```

3. **Configure the database:**

   Update the `application.properties` file in the `src/main/resources` directory with your database credentials.

    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/blog
    spring.datasource.username=blog
    spring.datasource.password=password
    spring.jpa.hibernate.ddl-auto=update
    ```

### Running the Application

1. **Build the project:**

    ```sh
    mvn clean install
    ```

2. **Run the application:**

    ```sh
    mvn spring-boot:run
    ```

   The application will start on `http://localhost:8080`.

### Running Tests

To run the tests, use the following command:

```sh
mvn test
```

### API Documentation

API documentation is available via Swagger. Once the application is running, you can access it at:

```sh
http://localhost:8080/swagger-ui.html
```
```sh
http://localhost:8080/api-docs
```
### Contact
Feel free to reach out if you have any questions or suggestions!

- **Email: knapp900@gmail.com**
- **LinkedIn: [Arthur Knapp](#https://www.linkedin.com/in/arthur-knapp-808a6a166/)**
- **GitHub: [Knapp900](#https://github.com/knapp900)**
