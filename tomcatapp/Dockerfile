FROM tomcat:9.0-slim
LABEL maintainer="jrmc0002@red.ujaen.es" 

WORKDIR /usr/local/tomcat/webapps

ADD ./webapp/target/webapp.war ./ROOT.war
EXPOSE 8080