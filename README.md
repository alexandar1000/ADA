<p align="center">
  <img width="250" height="250" src="ada-core/src/main/resources/logos/LogoMakr_8rK051.png">
</p>

# ADA - The Architectural Design Advisor

## General Information

This is a [Spring Boot](https://spring.io/projects/spring-boot), [PostgreSQL](https://www.postgresql.org/), and [Angular](https://angular.io/) application which helps developers and stakeholders analyse the architectural structure of GitHub Java projects. It focuses on visualising cohesion and coupling in and between classes. The implementation relies on [Hibernate](https://hibernate.org/) for interacting with the database, while the migrations are handled by [Flyway](https://flywaydb.org/). On the frontend, the graphs are made using [Cytoscape.js](https://js.cytoscape.org/). 
The project uses [an example Java Repository](https://github.com/MRHMisu/ADA-Example-Repository) to evaluate ADA's accurecy.

## Getting Started

### Prerequisites

In order to get the project started you will need to have the following software installed:
  - [Docker](https://www.docker.com/) 19+
  - [Docker-compose](https://docs.docker.com/compose/install/)
  - [Maven](https://maven.apache.org/)

Moreover, if you intend to develop the project, you will need to have the following software as well:
  - [Java 11](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html)
  - [Node.js](https://nodejs.org/en/)
  - [Lombok](https://projectlombok.org/) extension for your IDE; otherwise an error that certain methods have not been implemented will be shown in the IDE.

### Deployment

If you wish to deploy the application, this can be done easily as it has been fully dockerised.

#### Local Deployment

In order deploy the software locally, simply follow these steps:
1. Clone the repository
2. Set the profile to production in the `application.properties` file by setting `spring.profiles.active=prod`
3. In `ada-ui/src/environments/environment.prod.ts` set `backendBaseUrl` flag to the localhost address by edditing the corresponding line to: `'backendBaseUrl: 'http://localhost:18080/api/v1'`
4. run `mvn clean install`
5. run `sudo docker-compose up`
6. You are ready to use the application on `localhost:80`

#### Deployment to a Server
In order deploy the software to a server, follow these steps:
1. Clone the repository
2. Change the `postgres` and `pgadmin` credentials in the `docker-compose.yml` file
3. Set the profile to production in the `application.properties` file by setting `spring.profiles.active=prod`
4. In `ada-ui/src/environments/environment.prod.ts` set `backendBaseUrl` flag to the address of the server `www.server-address.com` by edditing the corresponding line to: `'backendBaseUrl: 'http://www.server-address.com:18080/api/v1'`
5.  Make sure that the the server allows incoming connections to the port 18080
6. run `mvn clean install`
7. run `sudo docker-compose up`
8. You are ready to use the application on `www.server-address.com`


### Development

In order to set up the project and get ready for development, follow these steps:
1. Pull the Git repository.
2. Import the project to your IDE using the `pom.xml` file.
3. For the purposes of development, comment out the `web_app_backend` and `web_app_frontend` sections from the `docker-compose.yml` file.
4. Set the profile to development in the `application.properties` file by editing `spring.profiles.active=prod` to `spring.profiles.active=dev`
5. Execute the docker-compose.yml file via running the `docker-compose up -d` command. You should be able to see that both the `postgres` and the `pgadmin` containers are running.
6. Open `pgadmin` by visiting `localhost:15050`. You can log in using the credidentials as stated in the `docker-compose.yml`.
    - **Email**: `ada-team@gmail.com`
    - **Password**: `ada-team`
7. Once `pgadmin` is running connect to the dev database server, with the following credentials:
    
    Dev Database:
    - **Name**: name the dev server as you wish.
    - **Host name/address**: `db_dev`
    - **Port**: `5432`
    - **Maintenance Database**: `ada`
    - **Username**: `ada-team`
    - **Password**: `1234`
 
    and press `Save`.
8. Run the application with the `./mvnw spring-boot:run` command.
9. The address `localhost:18080` is now the entry for the backend.
10. Change the directory to `ada-ui`.
11. Run `npm install`.
12. In order to serve the front end of your application, run `ng serve`.
13. Finally, you will be able to access the application at `localhost:4200`.

#### Possible Additional Setup

If you are using IntelliJ, you can connect to PostgreSQL through it. The process is similair to the one described above, however, the only difference is that you will have to now use `localhost` as the host, and `15432` as the port. This is due to the connection now being from the host to the container and not from within the docker network.

Also, it is worth mentioning that using Maven via IntelliJ is supported.

## Project structure

The project consists out of six modules:
- `ada-core` - the main part of the application, invoking and connecting all other components
- `ada-repository-downloader` - downloads the git repository and stores the metadata
- `ada-parser` - parses the source files and exposes the parsed data
- `ada-metric-calculator` - calculates the metrics based on the output from the parser module
- `ada-model` - defines the data model which is used by all other modules
- `ada-ui` - the user interface of the project


## Development Instructions

1. Writing unit tests is mandatory for all features which are written.
2. Code review and an approval are mandatory before a merge.
3. Prefix your branch names with your initials. An example would be: `asj_simpleBranchExample`.
4. Adhere to the migration instructions found in the following section.
5. Ask for help whenever you are stuck. Also, if you feel that you are progressing a bit slower, please feel free to mention it as soon as possible.

## Migration Instructions

All of the migrations should be saved in a `.sql` file and in the `ada-core/src/main/resources/db/migration/development` folder.
Flyway is used for handling the migrations. The explanation and the documentation can be found on their [website](https://flywaydb.org/documentation/migrations).

Keep in mind that the naming convention for the migrations should be as stated on the aforementioned website, with the emphasis on naming the versions by stating the year, month, day, hours and minutes in a dotted and zero-padded format. Again, also please take care to include the leading zeros. An example for a correct name would be: `V2020.01.24.19.45__Example_migration.sql`.

These points must be performed in order to populate the database once the server is deployed, and in order to be able to restore the database schema in an event of a failure.

The unexecuted migrations will be ran automatically upon the start of spring project.

## Dependencies

- Spring
- Spring Boot
- PostgreSQL
- Hibernate
- FlyWay
- Lombok
- Angular
- Node
- Cytoscape.js
 
## Helpful Docker Commands

- List all of the running containers `docker ps`
- List all containers `docker ps -a`
- Remove a container `docker container rm *container_id*`
- List docker images `docker image ls`
- Remove an image `docker image rm *image_id*`
- Starting the database containers: `docker-compose up -d`
- Stopping the database containers: `docker-compose stop`
- Removing the database containers: `docker-compose down`
- Pruning everything docker related: `docker system prune`
- Pruning the volumes: `docker volume prune`

Furthermore, a useful docker cheat sheet can be found [here](https://afourtech.com/guide-docker-commands-examples/).
