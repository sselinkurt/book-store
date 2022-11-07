FROM openjdk:11
EXPOSE 8080
ADD target/book-store.jar book-store.jar
ENTRYPOINT ["java", "-jar", "book-store.jar"]