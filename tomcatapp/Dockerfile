FROM tomcat:9.0-slim
LABEL maintainer="jrmc0002@red.ujaen.es" 

WORKDIR /usr/local/tomcat/webapps

RUN rm -rf *
ADD ./webapp/target/webapp.war ./ROOT.war
EXPOSE 8080