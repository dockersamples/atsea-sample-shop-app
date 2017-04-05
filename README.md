# Moby Art Store Demo Application

## Current build and run (this will be improved)
```bash
docker run -ti -v $(pwd):/mobyartshop -w /mobyartshop maven:alpine mvn package -DskipTests
docker-compose build
docker-compose up
```

(Unfortunately the above will most likely not work on Linux due to root bind
mount, you will need to get craftier with user remapping if executing there)

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

## Front end is being written in React and currently looks like this:

![Front End](frontend-2017-04-05)


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

## Configuration

### Static content
Make a directory for static content called 'static' in the same directory as the appication (MobyArtShop-0.0.1-SNAPSHOT.jar)

```
Application
|- MobyArtShop-0.0.1-SNAPSHOT.jar
|- static/
   |- client files
   |- images
   |_ ...

```

the URL for the content is http://localhost:8080/mobyartshop/*



## REST requests

### Products

#### Get all products

```
/mobyartshop/api/product/

GET /mobyartshop/api/product/

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
Returns:
```

[    
    {
        "description": "Keeping it safe and secure",
        "image": "/9j/4QAYRXhpZg ...."
        "name": "Trusted Registry",
        "price": 25.0,
        "productId": 32
    },
    {
        "description": "Moby at work",
        "image": "/9j/4QAYRXhpZg ...."
        "name": "Moby",
        "price": 25.0,
        "productId": 11
    }
]
```

#### Get single product (product detail)

```
/mobyartshop/api/product/{id}

GET /mobyartshop/api/product/{id}
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
Returns:
```
{
        "description": "Keeping it safe and secure",
        "image": "/9j/4QAYRXhpZg ...."
        "name": "Trusted Registry",
        "price": 25.0,
        "productId": 32
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
    "customerId" : 0,
    "name"       : "Sally Vallery",
    "address"    : "address as single string",
    "email"      : "sally@example.com".
    "phone"      : "phone as string"
    "username"   : "sallyv",
    "password"   : "sallypassword",
    "enabled"    : "true",
    "role"       : "USER"
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
Auth: 
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

### Get customer by name
```
/api/customer/name={name}

GET /api/customer/name={name}

Host: localhost:8080
Auth: 
Content-type: application/json
Accept: application/json
```

Returns:
```
{
    "customerId":9,
    "name":"Space Goat",
    "address":"1800 Nebula Rd",
    "email":"spacegoatlabel@gmail.com",
    "phone":"222-333-4444",
    "username":"spacegoat",
    "password":"spacegoatpass",
    "enabled":true,
    "role":"USER"
}
```

### Get customer by username
```
/api/customer/name={name}

GET /api/customer/username={username}

Host: localhost:8080
Auth: 
Content-type: application/json
Accept: application/json
```

Returns:
```
{
    "customerId":9,
    "name":"Space Goat",
    "address":"1800 Nebula Rd",
    "email":"spacegoatlabel@gmail.com",
    "phone":"222-333-4444",
    "username":"spacegoat",
    "password":"spacegoatpass",
    "enabled":true,
    "role":"USER"
}
```

### Update Customer
```
/api/customer/{customerId}

PUT /api/customer/{customerId}
Host: localhost:8080
Auth: 
Content-type: application/json
Accept: application/json

{
    "customerId" : 0,
    "name"       : "Sally Vallery",
    "address"    : "my new address",
    "email"      : "sally@example.com".
    "phone"      : "phone as string"
    "username"   : "sallyv",
    "password"   : "sallynewpassword",
    "enabled"    : "true",
    "role"       : "USER"
}

```

Returns:
```

OK 200
```

### Delete a Customer
```
/api/customer/{customerId}

DELETE /api/customer/{customerId}
Host: localhost:8080
Auth: 
Content-type: application/json
Accept: 

```

Returns:
```

OK 200
```

### Delete all Customers
```
/api/customer/

DELETE /api/customer/
Host: localhost:8080
Auth: 
Content-type: application/json
Accept: 

```

Returns:
```

OK 200
```

### Order requests

#### Create an order

```
/api/order/

POST /api/order/
Host: localhost:8080
Auth: basic username:password
Content-type: application/json
Accept: application/json

{
    "orderId" : 1,
    "orderDate : {current date},
    "customerId" : "54321",
    "productsOrdered" : {"1":1,"2":1,"3":1]
}

```
Returns:

```
orderId, orderNum

{
    "orderId": 1
}

```

#### Get all orders

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
        "orderId" : 1,
        "orderDate : {current date},
        "customerId" : "54321",
        "productsOrdered" : {"1":1,"2":1,"3":1]
    },
    {
        "orderId" : 2,
        "orderDate : {current date},
        "customerId" : "12345",
        "productsOrdered" : {"2":1,"3":1,"4":1]
    }
]
```
#### Get an order by id

```
/api/order/

GET /api/order/{orderId}
Host: localhost:8080
Auth: basic username:password
Content-type: application/json
Accept: application/json

Returns:

    {
        "orderId" : 1,
        "orderDate : {current date},
        "customerId" : "54321",
        "productsOrdered" : {"1":1,"2":1,"3":1]
    }
```
#### Update an order
```
/api/order/{orderId}

POST: /api/order/{orderId}
Host: localhost:8080
Auth: basic username:password
Content-type: application/json
Accept: application/json

curl -H "Content-Type: application/json" 
     -X PUT 
     -d ' {"orderId" : "0", "productsOrdered" : {"3":2,"6":3,"11":2} , "orderDate" : "2017-02-28T19:52:39Z", "customerId" : "8"}' 
     thedoctor:tardis@localhost:8080/mobyartshop/api/order/8

Returns:

{
    "customerId": 8,
    "orderDate": 1488311559000,
    "orderId": 8,
    "productsOrdered": {
        "11": 2,
        "3": 2,
        "6": 3
    }
}
```

#### Delete an order
```
/api/order/{orderId}

DELETE: /api/order/{orderId}
Host: localhost:8080
Auth: basic username:password
Content-type: application/json
Accept: application/json

curl -H "Content-Type: application/json" 
     -X DELETE 
     thedoctor:tardis@localhost:8080/mobyartshop/api/order/8

Returns:

 200 OK
```

### System Utilities

#### Database Healthcheck

 ```
/utility/healthcheck/

GET: /utility/healthcheck/
Host: localhost:8080
Auth: 
Content-type: application/json
Accept: application/json

curl -H "Content-Type: application/json" 
     -X GET 
     http://localhost:8080/mobyartshop/utility/healthcheck/

Returns:

{
    "status":"2017-03-27 03:01"
}

 ```

 #### Get Container Id
```
/utility/containerid/

GET: /utility/containerid/
Host: localhost:8080
Auth: 
Content-type: application/json
Accept: application/json

curl -H "Content-Type: application/json" 
     -X GET 
     http://localhost:8080/mobyartshop/utility/containerid/

Returns:

{
    "host": "spara-mbp",
    "ip": "192.168.0.6"
}
```
