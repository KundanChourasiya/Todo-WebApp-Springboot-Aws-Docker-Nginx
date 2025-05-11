# Todo-WebApp-Springboot-Aws-Docker-Nginx
![jsp](https://github.com/user-attachments/assets/9a67cbb7-ac7d-4f18-98ba-493daa73902c)

### Set up an AWS EC2 instance and install Docker to run Nginx as a reverse proxy.

# Overview
This guide covers the steps to:
  * Launch an AWS EC2 instance.
  * install Docker and docker-compose-v2.
  * Pull project from Github.
  * Create a dockerfile file.
  * Create a docker-compose.yml file.
  * Create a docker file for nginx.
  * Create a default.conf file to Configure Nginx server is running on the default port 80.
  * Configure the AWS EC2 instance's security group to allow access from anywhere on port 80.

# Follow the steps:
1. Create instance in AWS EC2.
2. Open an SSH client and connect to your instance.
3. Update the vm (EC2 instance)
     ```
     sudo apt-get update
     ```
4. Download and install docker.io in EC2.
     ```
     sudo apt-get install docker.io
     ```
 5. Download and install docker-compose-v2 in EC2.
    ```
    sudo apt-get install docker-compose-v2
    ```
 6. Check docker engine running stage.
    ```
    sudo systemctl status docker
    ```
 7. Add current user to docker group.
    ```
    sudo usermod -aG docker $USER
    ```
 8. Refresh the docker group.
    ```
    newgrp docker
    ```
 9. List out running docker containers.
     ```
     docker ps
    ```
 10. Create a folder with name in working directory.
     ```
     mkdir spring_project
     ```
11. Clone project from gitub inside the created folder.
    ```
    git clone https://github.com/KundanChourasiya/Todo-WebApp-Springboot-Aws-Docker-Ngnix.git
    ```
12. Create a Dockerfile inside the project directory and write the below code.
    ```
    vim Dockerfile
    ```
    * Dockerfile
     ```yml
     FROM maven:3.8.3-openjdk-17 AS builder
     WORKDIR /project
     COPY . /project
     RUN mvn clean install -DskipTests=true
     FROM openjdk:17-alpine
     WORKDIR /app
     COPY --from=builder /project/target/*.war /app/my-project.war
     CMD ["java", "-jar", "my-project.war"]
    ```
13. Create docker-compose file to build docker services.
    Note: here we can use env file also for environment variable.
     ```
     docker-compose.yml
     ```
    * docker-compose
    ```yml
    version: "3.8"
    services:
    
      nginx:
        container_name: nginx_cont
        build:
          context: ./nginx
        image: nginx
        ports:
          - "80:80"
        networks:
          - todo-network
        restart: always
        depends_on:
          - todo-app
    
      mysql:
        image: mysql:latest
        container_name: mysql
        environment:
          MYSQL_ROOT_PASSWORD: test
          MYSQL_DATABASE: tododb
        ports:
          - "3306:3306"
        volumes:
          - ./todo-data:/var/lib/mysql
        networks:
          - todo-network
        healthcheck:
          test: [ "CMD","mysqladmin","ping","-h","localhost","-uroot","-ptest" ]
          interval: 10s
          timeout: 5s
          retries: 5
          start_period: 30s
        restart: always
    
      todo-app:
        build: .
        container_name: todo-web-app
        environment:
          - SERVER_PORT=8080
          - PROFILE=prod
          - MYSQL_HOST=mysql
          - MYSQL_PORT=3306
          - MYSQL_DB=tododb
          - MYSQL_USERNAME=root
          - MYSQL_PASSWORD=test
        ports:
          - "8080:8080"
        networks:
          - todo-network
        depends_on:
          - mysql
        healthcheck:
          test: [ "CMD-SHELL", "curl -f http://localhost:8080 || exit 1" ]
          interval: 10s
          timeout: 5s
          retries: 5
          start_period: 30s
        restart: always
    
    
    volumes:
      todo-data:
    
    networks:
      todo-network:
        name: todo-network
        driver: bridge
    ```

15. check database url in project application.properties file there is “allowPublicKeyRetrieval=true&useSSL=false” public key retrieval or not, and check environment variable file the .env file like below.

    * application.properties
      ```properties
      # Server port (optional, defaults to 8080)
      server.port=${SERVER_PORT:8080}
      
      # spring profile active config
      spring.profiles.active=${PROFILE:dev}
      
      #viewResolver
      spring.mvc.view.prefix=/views/
      spring.mvc.view.suffix=.jsp
      ```

      * application-prod.properties
      ```properties
      #MySQL Database Configuration for production
      spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
      spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:${MYSQL_PORT:3306}/${MYSQL_DB:sbms}?allowPublicKeyRetrieval=true&useSSL=false
      spring.datasource.username=${MYSQL_USERNAME:root}
      spring.datasource.password=${MYSQL_PASSWORD:test}

      
      # JPA / Hibernate Configuration
      spring.jpa.hibernate.ddl-auto=update
      spring.jpa.show-sql=true
      spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
      ```

    * .env (environment variable file)
      ```properties
      SERVER_PORT=8080      # apps server port
      PROFILE=prod          # spring boot profile activate
      MYSQL_HOST=mysql      # mysql container name
      MYSQL_PORT=3306       # mysql container port
      MYSQL_DB=tododb       # database name
      MYSQL_USERNAME=root   # database username
      MYSQL_PASSWORD=test   # database password
      ```
16. Create a folder inside the project directory with name nginx and inside the folder create a docker file for create nginx image.
    * Dockerfile
    ```yml
    # pull nginx image
      FROM nginx:1.23.3-alpine
      
      COPY ./default.conf /etc/nginx/conf.d/default.conf
    ```
17. Create a default.conf file inside the nginx folder for Configure the nginx default port with host server ports.
    ```yml
    server {
          listen 80;          # Nginx server default port
          server_name localhost;      # DNS server name (www.abc.com)
      
          location / {
              proxy_pass http://crud-api:8080;        # running host and post no
              proxy_set_header Host $host;
              proxy_set_header X-Real-IP $remote_addr;
              proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
              proxy_set_header X-Forwarded-Proto $scheme;
          }
      }
    ```
18. Build the docker services.
    ```
    docker compose up -d --build
    ```
19. Configure the AWS EC2 instance's security group to allow access from anywhere on port 80, since the Spring Boot application is running on that port.
    ![image](https://github.com/user-attachments/assets/4ba50025-431a-4deb-9835-b656b3f6f1e0)

20. Open your browser, enter the URL with the port number, and submit some data to save it in the database.
> [!NOTE]
> Note: The Nginx server is running on the default port 80 and it forwards client requests to the backend server.

![image](https://github.com/user-attachments/assets/d129fd02-70e6-4321-aaaf-18ec45886f52)

![image](https://github.com/user-attachments/assets/c9c06b69-c82b-4e10-bedf-47113c376464)

![image](https://github.com/user-attachments/assets/ec7fd91a-d248-4bb5-8e02-35bb2fbdec3b)














