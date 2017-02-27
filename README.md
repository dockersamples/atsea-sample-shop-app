# Moby Art Store Demo Application

## The demo application is a store application with the following features:

* user account creation and sign-in 
* a products page listing all available products
* a detail page for an individual products
* a shopping cart to add products
* an order page that displays the products in the cart and a total

A page for each feature will be needed in the client.

## The application architecture includes:

* microservices REST backend written in Java and spring-boot to handle the store functions
* PostgreSQL database for product inventory, customer data, and order data
* Nginx proxies for serving static data and handling HTTPS request
* store front client written in Angular

It was recommended that the client site should be similar to https://www.kingandmcgaw.com/ 

## The operational architecture features:

### Swarm features supported and highlighted:
* Secrets
* Routing mesh
* Overlay networks
* Dynamic storage (infinit.io)
* Can run as a single stateless container
* Can be deployed as multi-container stateful app through compose v3 (docker stack deploy)
* Can be deployed on Docker 4 Mac for development demo
* Message queue
* Optimize for startup time

### UCP features supported and highlighted:
* L7 loadbalancing (HRM)
* HRM sticky sessions
* HRM HTTPS (SNI)

### Other ideas:
* Jmeter load generation
* NFS store for static content

## REST requests

### Get all products

```
/api/products

GET /api/products

```

### Get single product (product detail)

```
/api/products/{id}

GET /api/product/{id}
Host: localhost:8080
Auth: basic username:password
Content-type: application/json
Accept: application/json
{
    "productId" = 12345,
    "name" = "Moby",
    "description" = "Our mascot",
    "price" = "1000000.00",
    "image" = "image in 64bit encoded string"
}

```

### Create new cart

/api/customer/{id}/cart?cartId={id}

POST /api/customer/{id}/cart?cartId={id}
Host: localhost:8080
Auth: basic username:password
Content-type: application/json
Accept: application/json

{
    "cartId" = 98765
}

HTTP/1.1 201 Created
Date:
Content-Type: application/json

### Add to cart

```
/api/customer/{1d}/cart/{id}/add?productId={productId}&quantity=1

POST /api/customer/{1d}/cart/{id}/add
Host: localhost:8080
Auth: basic username:password
Content-type: application/json
Accept: application/json

{
    "productId" : 12345,
    "quantity" : 1
}

HTTP/1.1 201 Created
Date:
Content-Type: application/json
```

### Get cart contents

```
/customer/{id}/cart/{id}

GET /customer/{id}/cart/{id}
Host: localhost:8080
Auth: basic username:password
Content-type: application/json
Accept: application/json

{
    [
        {"productId" : 12345,
        "quantity" : 1},
        {"productId" : 12346,
        "quantity" : 2},
        {"productId" : 12347,
        "quantity" : 1},
    ]
}

HTTP/1.1 201 Created
Date:
Content-Type: application/json
Location: /customer/{id}/cart/{id}

```


