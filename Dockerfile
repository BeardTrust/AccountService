FROM openjdk:11
MAINTAINER Matthew.Crowell@Smoothstack.com
RUN adduser --system --group discoveryservice
USER discoveryservice:discoveryservice
COPY target/AccountService-0.0.1-SNAPSHOT.jar AccountService.jar
EXPOSE 7778
ENTRYPOINT ["java", "-jar", "AccountService.jar", "--spring.profiles.active=dev"]
