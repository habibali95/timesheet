FROM openjdk:11
EXPOSE 8099
COPY target/timesheet-1.0.jar timesheet-1.0.jar
ENTRYPOINT ["java","-jar","/timesheet-1.0.jar"]
