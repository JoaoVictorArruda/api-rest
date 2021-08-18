## API Rest

## How it works:
### **1. Docker. First, you need to install docker**
* Download Docker [Here](https://docs.docker.com/docker-for-windows/install/). Hint: Enable Hyper-V feature on windows and restart;
* Then open powershell and check:
```bash
docker info
```
or check docker version
```bash
docker -v
```
or docker compose version
```bash
docker-compose -v
```
### **2. Spring boot app**
* Clone the repository:
```bash
git clone https://github.com/JoaoVictorArruda/api-rest.git
```
* Build the maven project:
```bash
mvn clean install
```
* Running the containers:
  
This command will build the docker containers and start them.
```bash
docker-compose up
```
or

This is a similar command as above, except it will run all the processes in the background.
```bash
docker-compose -f docker-compose.yml up
```

All commands should be run from project root (where docker-compose.yml locates)

* If you have to want to see running containers. Checklist docker containers
```bash
docker container list -a
```
or
```bash
docker-compose ps
```
### **3. Docker control commands**
* Check all the images you have:
```bash
docker images
```
### **4. End stop app**
*  Stop containers:
```bash
docker-compose down
```
* Remove old stopped containers of docker-compose
```bash
docker-compose rm -f
```

# REST API DOCUMENTATION

## Get list of customers

### Request

`GET /api/customers/`

    curl -i -H 'Accept: application/json' http://localhost:8080/api/customers

### Response

    HTTP/1.1 200 OK
    Date: Wed, 18 Aug 2021 20:15:30 GMT
    Status: 200 OK
    Connection: close
    Content-Type: application/json
    Content-Length: 2

    [
      {
          "createdAt": "2021-08-18T12:23:33.247+00:00",
          "updatedAt": "2021-08-18T12:23:33.247+00:00",
          "id": 5,
          "name": "João",
          "cpf": "058.457.547-44",
          "address": "Longview Road"
      },
    ]

## Create a new customers

### Request

`POST /api/customers/:id`

    curl -i -X POST -H "Content-Type: application/json" -d "{\"name\":\"Joao\", \"cpf\":\"452.785.452-75\", \"address\":\"Forest Road\"}" http://localhost:8080/api/customers/

### Response

    HTTP/1.1 201 Created
    Date: Wed, 18 Aug 2021 20:16:54 GMT
    Status: 201 Created
    Connection: close
    Content-Type: application/json

    {"createdAt": "2021-08-18T12:23:33.247+00:00", "updatedAt": "2021-08-18T20:16:54.247+00:00", "id": 1, "name": "João", "cpf": "452.785.452-75", "address": "Forest Road"}

## Get a specific customer

### Request

`GET /api/customers/:id`

    curl -i -H 'Accept: application/json' http://localhost:8080/api/customers/1

### Response

    HTTP/1.1 200 OK
    Date: Wed, 18 Aug 2021 20:31:31 GMT
    Status: 200 OK
    Connection: close
    Content-Type: application/json

    {"createdAt": "2021-08-18T12:23:33.247+00:00", "updatedAt": "2021-08-18T20:16:54.247+00:00", "id": 1, "name": "João", "cpf": "452.785.452-75", "address": "Forest Road"}

## Get a non-existent customer

### Request

`GET api/customers/id`

    curl -i -H 'Accept: application/json' http://localhost:8080/api/customers/9999

### Response

    HTTP/1.1 404 Not Found
    Date: Wed, 18 Aug 2021 20:31:31 GMT
    Status: 404 Not Found
    Connection: close
    Content-Type: application/json

    {"timestamp":"2021-08-18T20:31:24.048+00:00","status":404,"error":"Not Found","message":"","path":"/api/customers/9999"}

## Change a customer's state

### Request

`PUT /api/customers/:id`

    curl -i -X PUT -H "Content-Type: application/json" -d "{\"name\":\"Joao Arruda\", \"cpf\":\"854.524.699-22\", \"address\":\"Street Road\"}" http://localhost:8080/api/customers/1

### Response

    HTTP/1.1 200 OK
    Date: Wed, 18 Aug 2021 20:18:04 GMT
    Status: 200 OK
    Connection: close
    Content-Type: application/json

    {"createdAt":"2021-08-18T20:17:31.845+00:00","updatedAt":"2021-08-18T20:18:04.845+00:00","id":1,"name":"Joao Arruda","cpf":"854.524.699-22","address":"Street Road"}

## Delete a customer

### Request

`DELETE /api/customers/:id`

    curl -i -H 'Accept: application/json' -X DELETE http://localhost:8080/api/customers/1

### Response

    HTTP/1.1 204 No Content
    Date: Wed, 18 Aug 2021 20:25:54 GMT
    Status: 204 No Content
    Connection: close


## Try to delete same customer again

### Request

`DELETE /api/customers/:id`

    curl -i -H 'Accept: application/json' -X DELETE http://localhost:8080/api/customers/1/

### Response

    HTTP/1.1 404 Not Found
    Date: Wed, 18 Aug 2021 20:25:59 GMT
    Status: 404 Not Found
    Connection: close
    Content-Type: application/json

    {"timestamp":"2021-08-18T20:25:54.700+00:00","status":404,"error":"Not Found","message":"","path":"/api/customers/1"}

