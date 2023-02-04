FROM openjdk:latest

COPY target/CentralUsuarios.jar /CentralUsuarios.jar

ENTRYPOINT ["java" , "-jar" , "-Dspring.profiles.active=release" ,"/CentralUsuarios.jar"]