IMAGE=dasn/javawebapp

dev: build
	docker-compose up -d
	docker-compose ps
build: 
	mvn -f src/pom.xml clean package
	docker-compose build 
	@echo  Imagen $(IMAGE) creada
clean:
	-mvn -f src/pom.xml clean
	-docker-compose down 
	-docker rmi $(IMAGE)
	@echo Imagen $(IMAGE) borrada

