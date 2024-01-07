# FuelApp Installation Guide

## Prerequisites
- [Docker](https://www.docker.com/) installed on your machine
- Java Runtime Environment (JRE) version 17 installed

## Steps to Install and Run

1. **Clone the Repository:**
    ```bash
    git clone git@gitlab.fit.cvut.cz:stastpa8/bi-tjv.git
    cd bi-tjv
    ```

2. **Build and Run with Docker Compose:**
    ```bash
    docker-compose up -d --build
    ```
    This command will build the Docker images and start the containers in detached mode.

3. **Access the Client Container:**
    ```bash
    docker exec -it client sh
    ```

4. **Run the Java Application:**
    ```bash
    java -jar client-0.0.1-SNAPSHOT.jar
    ```
    This command will execute the Java application inside the client container.

5. **Access the Application:**
    For any information type `help` in the shell command line.

6. **API Documentation:**
    Explore the API documentation at [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html).

### Exiting the Application
To exit the application, perform the following steps:

- In the console where the Java application is running, type `exit` and press Enter.

- Stop the Docker containers by running:
    ```bash
    docker-compose down
    ```
### Troubleshooting

If you encounter any issues or errors during the installation or execution, please check the application logs and Docker logs for more information.

### Custom Configuration

If you need to customize any configuration parameters, refer to the `docker-compose.yml` file and update the necessary values.
