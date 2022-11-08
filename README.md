# Book-Store

Simple book store application backend project

Tech Stack:

- JDK 11
- Spring Boot
- Maven
- H2
- Spring Data JPA
- Spring Security
- Docker

1-) git clone https://github.com/sselinkurt/book-store.git

2-) Open the project on your favorite IDE and run the BookStoreApplication

3-) To see tables on h2 database: http://localhost:8080/h2-console/

4-) Get token from http://localhost:8080/authenticate endpoint.

Example request body:

    {
       "username": "guest",
       "password": "123456"
    }

5-) Add the token returned from above request to request header with Authorization key to use other APIs.


* Dockerize Application Steps

    - Build jar with Maven
    - docker build -t book-store.jar .  (Create image)
    - docker run – p 9090:8080 book-store.jar
    
