FROM node:latest
MAINTAINER jrbalsas@ujaen.es

WORKDIR /app

COPY src/package*.json ./

RUN npm install
COPY src/ ./

#ENV PORT=4000
EXPOSE 3000

ENTRYPOINT ["node","app.js"]

