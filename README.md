<p align="center">
  <img width="250" height="250" src="ada-core/src/main/resources/logos/LogoMakr_8rK051.png">
</p>

# ADA - The Architectural Design Advisor

### The repository of ADA, the Architectural Design Advisor

## General Information


The project and sprint backlogs can be found on the project's [Trello board](https://trello.com/invite/b/CHtfAIFN/d2ebc24144c32afd61693a4605d8c898/ada), and the group report on [Overleaf](https://www.overleaf.com/read/fcsmpbsrfndf).

The project uses [a simple Java Android application](https://github.com/alexandar1000/ADA-test-simple-Java-project) for testing.

## Getting Started

### Prerequisites

In order to get the project started you will need to have [docker](https://www.docker.com/) 19+ installed, along with [compose](https://docs.docker.com/compose/install/). 

Furthermore, this project uses [java 11](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html), so please make sure that you have it installed prior to development.

Finally, depending on your environment, you might need to install [maven](https://maven.apache.org/).

### Setting up the project

In order to set up the project and get ready for development, follow these steps:
1. Pull the Git repository.
2. Import the project to your IDE using the `pom.xml` file.
3. Execute the docker-compose.yml file via running the `docker-compose up -d` command. You should be able to see that both the `postgres` and the `pgadmin` containers are running.
4. Open `pgadmin` by visiting `localhost:15050`. You can log in using the credidentials as stated in the `docker-compose.yml`.
    - **Email**: `ada-team@gmail.com`
    - **Password**: `ada-team`
 5. Once `pgadmin` is running connect to the dev and test database servers, with the following credentials:
    
    Dev Database:
    - **Name**: name the dev server as you wish.
    - **Host name/address**: `db_dev`
    - **Port**: `5432`
    - **Maintenance Database**: `ada`
    - **Username**: `ada-team`
    - **Password**: `1234`
    
    Test Database:
    - **Name**: name the test server as you wish.
    - **Host name/address**: `db_test`
    - **Port**: `5432`
    - **Maintenance Database**: `ada`
    - **Username**: `ada-team`
    - **Password**: `1234`
 
    and press `Save`.
6. Run the application with the `./mvnw spring-boot:run` command.
7. Open `localhost:8080` and enjoy the masterpiece.

### Possible Additional Setup

If you are using IntelliJ, you can similarily connect to PostgreSQL through it. The only difference is that you would have to now
use the ports `15432` and `25432` respectively. This is due to the connection now being from the host to the container and not from within the docker network.

Also, using Maven is a lot easier through IntelliJ.

## Project structure

The project consists out of four modules:
- ada-core - the main part of the application, invoking and connecting all other components
- ada-repository-downloader - downloads the git repository and stores the metadata
- ada-parser - parses different languages and exposes the parsed data
- ada-metric-calculator - calculation of the metrics based on the output from the parser component

## Deployment

Deployment has not yet been dockerized, but will come soon.

## Development Instructions

1. Writing unit tests is mandatory for all features which are written.
2. Code review and an approval are mandatory before a merge.
3. Master branch is to be updated only in the end of a sprint. 
4. During the sprints all new features are to be developed on branches branching out from development, and are to be mearged back into development.
5. Prefix your branch names with your initials. An example would be: `asj_simpleBranchExample`.
6. Adhere to the migration instructions found in the following section.
7. Please actively use Trello and mark the features which you are working on in order not to have overlaps.
8. If you believe new tasks should be added to the Trello board, please feel free to add them to the `project backlog`.
9. Ask for help whenever you are stuck. Also, if you feel that you are progressing a bit slower, please feel free to mention it as soon as possible.

## Migration Instructions

All of the migrations should be saved in a `.sql` file and in the `src/main/resources/db/migration` folder.
Flyway is used for handling the migrations. The explanation and the documentation can be found on their [website](https://flywaydb.org/documentation/migrations).

Keep in mind that the naming convention for the migrations should be as state on the aforementioned website, 
 with the emphasis of naming the versions by stating the year, month, day, hours and minutes in a dotted and zero-padded format.
 Again, please take care of the leading zeros. An example for this would be: `V2020.01.24.19.45__Example_migration.sql`.

This must be done in order to populate the database once the server is deployed, and to be able to restore the database schema in an event of a failure.

The unexecuted migrations will be ran automatically upon the start of spring project.

## Dependencies

- Spring
- Spring Boot
- Hibernate
- FlyWay
- Lombok
 
## Helpful Docker Commands

- List all of the running containers `docker ps`
- List all containers `docker ps -a`
- Starting the database containers: `docker-compose up -d`
- Stopping the database containers: `docker-compose stop`
- Removing the database containers: `docker-compose down`
- Pruning everything docker related: `docker system prune`
- Pruning the volumes: `docker volume prune`

Furthermore, a useful docker cheat sheet can be found [here](https://afourtech.com/guide-docker-commands-examples/).
