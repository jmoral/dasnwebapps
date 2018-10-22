FROM tomcat:9.0-slim
MAINTAINER jrbalsas@ujaen.es

WORKDIR /usr/local/tomcat/webapps

RUN rm -fr *
COPY ./src/target/javawebapp-1.0-SNAPSHOT.war ./ROOT.war

EXPOSE 8080


