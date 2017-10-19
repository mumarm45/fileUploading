# Spring Boot File Upload Example #
Clustered Data Warehouse

Suppose you are part of a scrum team developing data warehouse for Bloomberg to analyze FX deals. One of customer stories is to import deals details from files into DB. The requested performance is to be able to import the file containing 100,000 records in less than 5 seconds.


Request logic as following :

File format is CSV contains the following fields (Deal Unique Id, From Currency ISO Code "Ordering Currency", To Currency ISO Code, Deal timestamp, Deal Amount in ordering currency).
Validate row structure.
Valid rows should be stored in table/document, with reference to source file name .
Invalid rows should be stored into another table/document, with reference to source file name.
The DB contains another table to maintain accumulative count of deals per Ordering Currency "Columns : Currency ISO Code, CountOfDeals ", so upon completion of importing process the system should increase count of deals per currency.
System should not import same file twice.
No rollback allowed, what every rows imported should be saved in DB.

Technical Specs :

Access to DB should be through JPA.
For DB type, you can select between (MySql or MongoDB)
Provide a web interface for uploading files and inquire about results "using filename" following web applications 3 tier architecture. Spring Batch is not allowed.

Deliverables should be ready to work including :

Workable deployment including sample file.
Deployment steps including sample data of 100K records, contains all invalid/valid records that the system handles.
Maven or Gradle project is required for full source code.
Proper error/exception handling.
Proper Logging.
It is essential to follow TDD and include unit testing of your code and provide it as part of the assignment, noting that this will be one of the major assessment points, minimum code coverage should be 70%.
It is preferred to deliver via github or bitbucket repository.
Provide deployment as a Vagrant or Docker is a plus.


##Solution of the above mentioned problem
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
uploadFile.path|/upload-dir|Root upload directory

server.port|8080|Listening port


application.yml
```properties
spring.datasource.url= jdbc:mysql://localhost:3306/cust
fileUpload.path=/upload-dir
fileUpload.separator= ','
server.port=8080
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


