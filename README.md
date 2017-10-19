# Spring Boot File Upload Example #



### Requirements

Java Platform (JDK) 8 
## Getting Started

First step is to clone this repository. You can compile and run this as any other Spring Boot application, if you are
not familiar with Spring Boot, a simple way is to run the below commands from the root directory (where the pom.xml is located),
If maven install on your system then run these command.
Compile and package
```bash
./mvnw clean package
```
Run the server
```bash
java -jar target/spring-boot-server-1.0.0.jar
```
 
### Server configuration

To configure the server, you can add the below properties in the application.properties file or set them 
when running the server from the command line,

Property | Default | Description
---------|---------|-------------
<<<<<<< HEAD
fineuploader.base-dir|./uploads|Root upload directory
=======
uploadFile.path|/upload-dir|Root upload directory
>>>>>>> e70a026400720056b271ae73dfd69425f0f9eb19
server.port|8080|Listening port


application.yml
```properties
spring.datasource.url= jdbc:mysql://localhost:3306/cust
fileUpload.path=/upload-dir
fileUpload.separator= ','
<<<<<<< HEAD
server.port=9090
=======
server.port=8080
>>>>>>> e70a026400720056b271ae73dfd69425f0f9eb19
spring.datasource.username: root
spring.datasource.password: root
spring.datasource.driver-class-name: com.mysql.jdbc.Driver
```

Command line configuration
```bash
java -Dserver.port=9090  -jar target/spring-boot-server-1.0.0.jar
```

### Server endpoints
Method | Endpoint | Usage
-------|----------|-------
POST|/deal|Upload file end point.  Make multipartfile request in file elemet.


<<<<<<< HEAD
project is licensed under the terms of the MIT license.
=======
project is licensed under the terms of the MIT license.
>>>>>>> e70a026400720056b271ae73dfd69425f0f9eb19
