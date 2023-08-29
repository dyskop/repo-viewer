# repo-viewer

solution of the Atipera test task

### Quick testing 

- The application has been deployed on `AWS EC2 Instance` and is available at the `44.211.72.87` public IP and `8080` port. So to test the application you just can use the link http://44.211.72.87:8080/api/v1/repos/{username}, replace the `username` with whatever you like, e.g. `dyskop` which is my username, and check the API via `Postman HTTP-client`. You can import and use my prepared `Postman` request collection located in the project directory called `doc`. Note that the `GitHub Rest API` has a restriction on unauthorized requests, and to extend it if necessary, you should add an `Authorization` request header with your personal `GitHub access token`. And also you can find the `Swagger API` definition [here](http://44.211.72.87:8080/swagger-ui.html).

### Testing the solution with Docker

Alternatively, you can run the application and test the API locally using `Docker`:

- download the project from the `GitHub` repository [repo-viewer](https://github.com/dyskop/repo-viewer.git);
- open the project in `IDE` and build using `gradle build` terminal command;
- run the application in docker-container using `docker-compose up -d` terminal command;
- import the collection of requests from the `doc` directory into `Postman HTTP-client` and test this `get` endpoint `http://localhost:8080/api/v1/repos/{username}` with different path variable values and `Accept` request header value.
- end work with the application with the `docker-compose down` terminal command;
- clean up docker resources with the `docker image rm repo-viewer-app` terminal command.

### Used technologies

- Java 17
- Spring-Boot 3
- Spring-WebFlux
- Mapstruct
- Lombok
- JUni 5
- Gradle
- Docker
- Git
- AWS EC2 Instance
- Swagger