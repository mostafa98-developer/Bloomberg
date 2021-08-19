# Start with a base image containing java runtime 
FROM java:1.8

# Make port
EXPOSE 8081

ADD target/Backend.jar Backend.jar

# run the jar file 
ENTRYPOINT ["java", "-jar", "Backend.jar"]