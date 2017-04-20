#  AtSea Shop Demonstration Application

The AtSea Shop is a demonstration application comprised of: 

* Java REST application written using Spring-Boot, 
* a database for product inventory, customer data, and orders,
* a React shopping cart,
* a NGINX reverse proxy implementing https,
* a payment gateway to simulate certificate management

# Building and Running the AtSea Shop

## Run as an application

To run the AtSea shop as an application:
```
docker-compose up --build
```

## Deploy to a swarm
```
#If you need to create a Swarm
docker swarm init
docker stack deploy -c docker-stack.yml atsea
```

## The AtSea Shop 

The URL for the content is `http://localhost:8080/`

# REST API

Documentation for REST calls: [REST API](./REST.md)


