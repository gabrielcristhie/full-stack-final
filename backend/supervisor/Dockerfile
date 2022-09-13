FROM openjdk:11
VOLUME /tmp
ADD ./target/supervisor-0.0.1-SNAPSHOT.jar supervisor.jar
ENTRYPOINT ["java","-jar","/supervisor.jar"]