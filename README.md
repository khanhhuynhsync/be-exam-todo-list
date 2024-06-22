# be-exam-todo-list
* Java 17
* Quarkus
* Build native image
* H2 database
* Flyway
* Basic authentication

1. Build
   * Command to build native image, need docker: ```mvn clean package -Pnative```
   * Command to build jar: ```mvn clean package```

2. Run:
   * Command to run native image: ```./target/be-exam-todo-list-1.0.0-runner```
   * Command to run in dev mode: ```mvn quarkus:dev```

3. API, postman collection is available in the root folder [TODO LIST.postman_collection.json](TODO%20LIST.postman_collection.json)

# Endpoints

### GET http://localhost:8080/ping
>No authentication required
<hr>

### POST http://localhost:8080/useraccount/create
>No authentication required

>Only "admin" and "user" roles are available right now

Request body:

```json
{
    "username": "admin",
    "password": "admin",
    "role": "admin"
}
```
<hr>

### POST http://localhost:8080/todos/create
>Basic authentication required

>Roles allowed: "admin" and "user"

Request body:

```json
{
    "title": "title",
    "content": "content"
}
```
<hr>

### GET http://localhost:8080/todos/listall
>Basic authentication required

>Roles allowed: "admin"
<hr>

### GET http://localhost:8080/todos/{{id}}
>Basic authentication required

>Roles allowed: "admin"
<hr>

### DELETE http://localhost:8080/todos/{{id}}
>Basic authentication required

>Roles allowed: "admin"
<hr>

### GET http://localhost:8080/todos/me
>Basic authentication required

>Roles allowed: "admin" and "user"
<hr>

### GET http://localhost:8080/todos/me/{{id}}
>Basic authentication required

>Roles allowed: "admin" and "user"
<hr>

### DELETE http://localhost:8080/todos/me/{{id}}
>Basic authentication required

>Roles allowed: "admin" and "user"
