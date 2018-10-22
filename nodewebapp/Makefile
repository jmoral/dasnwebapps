IMAGE=dasn/nodewebapp
LOCALPATH=src

dev: build 
	docker-compose up -d
	docker-compose ps
build: clean 
	docker-compose build 
	@echo  Imagen $(IMAGE) creada
clean: cleandependencies
	-docker-compose down 
	-docker rmi $(IMAGE)
	@echo Imagen $(IMAGE) borrada
cleandependencies:
	-rm $(LOCALPATH)/notes.sqlite
	-rm -fr $(LOCALPATH)/node_modules

