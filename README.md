# KG CRM POC

This project is POC for example crm 

## RUNNING PROJECT ON LOCAL

In comment line you can use this script for running jar. But first you run below script
```bash
mvn clean install
```

than you go to **target** folder and run below script.

```bash
java -jar service-name-0.0.1-SNAPSHOT.jar
```

## RUNNING PROJECT ON DOCKER

run the docker image
```bash
docker run -it -p 8080:8080 kg-poc/app

```

## DEBUGGING PROJECT ON LOCAL

### On Intellij Idea

Click 'Edit Configuration' than click '+' button and search 'Spring Boot'.
Opening window you must set main you must set main class in project '**Application.java' and  now you can start debugging your project


## Service Links from local

```
Rest Swagger documentations --> http://localhost:9060/api-documentation/swagger-ui.html
```

## Database