# MySQL Docker + Java Sample

This project provides a setup to run a MySQL 5.7 instance using Docker Compose and a Java application that connects to it, creates a table, inserts data, and reads it back.

## Prerequisites

1.  **Docker Desktop** (or Docker Engine + Compose plugin)
2.  **Java JDK 17+**
3.  **Maven**

## How to Run

### 1. Start the Database
Ensure Docker is installed and running. Open a terminal in this directory and run:

```bash
docker-compose up -d
```
*Note: If `docker-compose` command is not found, try `docker compose up -d`.*

This will start a MySQL 5.7 container listening on port `3306`.
- **Database**: `testdb`
- **User**: `user`
- **Password**: `password`
- **Root Password**: `root_password`

### 2. Run the Java Application
Once the database is up (wait a few seconds for it to initialize), run the Java application using Maven:

```bash
mvn clean compile exec:java
```

Or you can package and run the JAR:

```bash
mvn clean package -DSkipTest
java -cp "target/mysql-docker-sample-1.0-SNAPSHOT.jar;target/dependency/*" com.example.Main
```

### 3. Cleanup
To stop and remove the container:

```bash
docker-compose down
```

## Troubleshooting
- **Connection Refused**: Ensure the container is running (`docker ps`) and fully healthy.
- **Port Conflict**: If port 3306 is already in use by another MySQL instance, modify `docker-compose.yml` to use a different host port (e.g., `"3307:3306"`) and update the URL in `Main.java`.

## Database Management

### Connecting to the Container
To access the MySQL shell inside the running container:

```bash
podman exec -it mysql-docker-container mysql -uroot -proot_password
```

### Manual Database Setup
If needed (e.g., if not using the provided `docker-compose.yml`), you can run these SQL commands after connecting:

```sql
create database db_example;
create user 'springuser'@'%' identified by 'ThePassword';
grant all on db_example.* to 'springuser'@'%';
show databases;
SELECT User, Host FROM mysql.user;
```

curl localhost:8080/friend/add -d name=JaneCH 
curl localhost:8080/friend/all  

# APM Agent
### install
```bash
curl -L -O https://artifacts.elastic.co/downloads/beats/elastic-apm-agent/elastic-apm-agent-1.45.0.jar
```
### run
```bash
java -javaagent:java-monitoring/agent/elastic-apm-agent-1.45.0.jar \
 -Delastic.apm.service_name=mysqlservice \
 -Delastic.apm.server_urls= \
 -Delastic.apm.secret_token= \
 -Delastic.apm.application_packages=com.example \
 -jar mysql-service/target/mysql-service-0.0.1-SNAPSHOT.jar
```
