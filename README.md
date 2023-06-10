# SpringBoot Microservices Monorepo (sb-msrv-monorepo)
========

Mono repo for an ecommerce type, based on microservices architecture using Spring Boot, OpenFeign and H2 db.
> **Note:** OpenJDK v17.0.7 have been used to create this project. :pushpin: . Also Docker and docker-compose is required to run all the
> microservices seamlessly.
> 
> Download it from here [OpenJDK 17.0.7](https://openjdk.org/projects/jdk/17/)

## Preparing for Development
Follow these steps to start developing with this project:
1. Ensure `jdk 17` is installed on your machine
2. Clone the repository: `git clone git@github.com/edenilsonpineda/sb-msrv-monorepo)`
3. `cd` into the repository
4. `cd` into the desired service to work with (e.g the authorization-sevice `cd authorization-service`)
5. Build the project with `./gradlew clean build assemble -x test`
6. Run the project: `./gradlew bootRun`


## Run all the services using docker-compose
Follow these steps to start developing with this project:
1. Ensure `docker` and `docker-compose` is installed on your machine
2. Clone the repository: `git clone git@github.com/edenilsonpineda/sb-msrv-monorepo)`
3. `cd` into the repository
6. Run compose: `docker-compose up -d`


## Notes

Ports used: 🔒
- Auth service: 9081
- Products service: 9082
- Orders service: 9083
- Order details service: 9084
- Payments service: 9085


Now you should be able to consume the RESTs APIs with the provided `Postman-Collection.json`