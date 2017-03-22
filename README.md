# Moby Art Store Demo Application

## Current build and run (this will be improved)
```bash
mvn package
cp -f target/MobyStore-0.0.1-SNAPSHOT.jar app/
docker-compose build
docker-compose up
```

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

## Front end is adopted from [ngCart](http://ngcart.snapjay.com/) and currently has a wireframe interface, not connected to the actual app.

![ngCartwireframe](ngcart.png)


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

### Products

#### Get all products

```
/MobyStore/api/product/

GET /MobyStore/api/product/

Host: localhost:8080
Auth: basic username:password
Content-type: application/json
Accept: application/json
{
    ["productId" : 12345,
    "name" : "Moby",
    "description" : "Our mascot",
    "price" : "1000000.00",
    "image" : "image in 64bit encoded string"],
     ["productId" : 12346,
    "name" : "Gordon",
    "description" : "Turtle",
    "price" : "1000000.00",
    "image" : "image in 64bit encoded string"],

    ...

}
```

#### Get single product (product detail)

```
/MobyStore/api/product/{id}

GET /MobyStore/api/product/{id}
Host: localhost:8080
Auth: basic username:password
Content-type: application/json
Accept: application/json
{
    "productId" : 12345,
    "name" : "Moby",
    "description" : "Our mascot",
    "price" : "1000000.00",
    "image" : "image in 64bit encoded string"
}

```

### Customer requests

#### Create customer

```
/api/customer/

POST /api/customer?new:{customerId}&password:{password}&username:{username}
Host: localhost:8080
Auth: basic username:password
Content-type: application/json
Accept: application/json

{
    "customerId" : 54321,
    "name" : "Sally Vallery",
    "address" : "address as single string",
    "email" : "sally@example.com".
    "phone" : "phone as string"
    "username" : "sallyv",
    "password" : "sallypassword"
}
```
Returns:

```
customerId

{
    "customerId": 1
}

```

#### Get customer
```
/api/customer/{id}

GET /api/customer/{id}

Host: localhost:8080
Auth: basic username:password
Content-type: application/json
Accept: application/json

{
    "customerId" : 54321,
    "name" : "Sally Vallery",
    "address" : "address as single string",
    "email" : "sally@example.com".
    "phone" : "phone as string"
    "username" : "sallyv",
    "password" : "sallypassword"
}
```
### Order requests

#### Add item 

```
/api/order/

POST /api/order/
Host: localhost:8080
Auth: basic username:password
Content-type: application/json
Accept: application/json

{
    "orderId" : 12345,
    "orderDate : {current date},
    "orderNum" : 12345,
    "customerId" : "54321",
    "productId" : "2"
}

```
Returns:

```
orderId, orderNum

{
    "orderId": 1,
    "orderNume" " 1
}

```
```

#### Delete item

```
/api/order/{orderId}

DELETE /api/order/{orderId}
Host: localhost:8080
Auth: basic username:password
Content-type: application/json
Accept: application/json

``` 
#### Get all orders/items

```
/api/order/

GET /api/order/
Host: localhost:8080
Auth: basic username:password
Content-type: application/json
Accept: application/json

Returns:

[
    {
        "customerId": 1,
        "orderDate": "2017-03-08",
        "orderId": 2,
        "orderNum": 3,
        "productId": 6
    },
    {
        "customerId": null,
        "orderDate": "2017-03-08",
        "orderId": 4,
        "orderNum": 1,
        "productId": 5
    }
]
```

#### Get all items in an order

```
/api/order/orderItems={orderNum}

GET /api/order/orderItems={orderNum}
Host: localhost:8080
Auth: basic username:password
Content-type: application/json
Accept: application/json

Returns:

[
    {
        "customerId": 1,
        "orderDate": "2017-03-08",
        "orderId": 2,
        "orderNum": 3,
        "productId": 6
    },
    {
        "customerId": 1,
        "orderDate": "2017-03-08",
        "orderId": 4,
        "orderNum": 3,
        "productId": 9
    }
]
```
#### Get a single item added to an order
```
/api/order/{orderId}

GET /api/order/{orderId}
Host: localhost:8080
Auth: basic username:password
Content-type: application/json
Accept: application/json

Returns:

{
        "customerId": 1,
        "orderDate": "2017-03-08",
        "orderId": 2,
        "orderNum": 3
    }

```

