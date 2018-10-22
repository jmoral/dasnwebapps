# Sample container with Node Insecure app

## Requeriments

Docker, make

## Usage
You can control the local container with ```docker-compose``` commands, i.e. up, stop, start, down, etc. however, if available, the ```make``` utility can be used to simplify usual operations.

- Build app, container and launch container

```bash
$ make
```
- Build container image

```bash
$ make build
```
- Stop container and remove Image

```bash
$ make clean
```

## Options
- Database must be initialized in url <http://localhost:8080/initdb>
