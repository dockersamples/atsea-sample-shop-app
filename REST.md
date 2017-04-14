# REST Requests

The REST endpoints are divided into three types:

1. API endpoints are for creating and managing data about products, customers, and orders.
2. Administrative endpoints are for actions such as login and purchase
3. Utility endpoints are for requesting information about the system

# API Endpoints

# Product Requests

## Get All Products
****Request:****
```
GET /atsea/api/product/

Host: localhost:8080
Auth: 
Content-type: application/json
Accept: application/json

```
****Returns:****
```
HTTP 200 OK

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
****Error:****
```
HTTP 204 NO CONTENT
```

## Get Single Product
**Request:**
```
GET /atsea/api/product/{id}

Host: localhost:8080
Auth:
Content-type: application/json
Accept: application/json
```
**Returns:**
```
{
        "description": "Keeping it safe and secure",
        "image": "image/img1.png"
        "name": "Trusted Registry",
        "price": 25.0,
        "productId": 32
}
```
**Error:**
```
HTTP 404 NOT FOUND
```

# Customer Requests

## Create Customer
**Request:**
```
POST /atsea/api/customer/

Host: localhost:8080
Auth: 
Content-type: application/json
Accept: application/json

{
    "customerId" : 0,
    "name"       : "Sally Vallery",
    "address"    : "144 Townsend, San Francisco 99999",
    "email"      : "sally@example.com",
    "phone"      : "513 222 5555",
    "username"   : "sallyv",
    "password"   : "sallypassword",
    "enabled"    : "true",
    "role"       : "USER"
}
```
**Returns:**
```
customerId

{
    "customerId": 1
}
```
**Error:**
```
HTTP 409 CONFLICT
{
    "error" : "Unable to create customer with username xxxx"
}
```
## Get Customer
**Request:**
```
GET /atsea/api/customer/{id}

Host: localhost:8080
Auth: 
Content-type: application/json
Accept: application/json
```
**Returns:**
```
{
    "customerId" : 54321,
    "name" : "Sally Vallery",
    "address" : "144 Townsend, San Francisco 99999",
    "email" : "sally@example.com",
    "username"   : "sallyv",
    "phone" : "513 222 5555"
}
```
**Error:**
```
HTTP 404 NOT FOUND
{
    "error":"Customer with id 1 not found."
}
```

## Get Customer by Name
**Request:**
```
GET /atsea/api/customer/name={name}

Host: localhost:8080
Auth: 
Content-type: application/json
Accept: application/json
```
**Returns:**
```
{
    "customerId":9,
    "name":"Space Goat",
    "address":"1800 Nebula Rd",
    "email":"spacegoatlabel@gmail.com",
    "phone":"222-333-4444",
    "username":"spacegoat"
}
```
**Error:**
```
HTTP 404 NOT FOUND
{
    "error":"Customer with name Space Goat not found"
}
```
### Get Customer by Username
**Request:**
```
GET /atsea/api/customer/username={username}

Host: localhost:8080
Auth: 
Content-type: application/json
Accept: application/json
```
**Returns:**
```
{
    "customerId":9,
    "name":"Space Goat",
    "address":"1800 Nebula Rd",
    "email":"spacegoatlabel@gmail.com",
    "phone":"222-333-4444",
    "username":"spacegoat"
}
```
**Error:**
```
HTTP 404 NOT FOUND
{
    "error":"Customer with username xxx not found"
}
```
## Update Customer
**Request:**
```
PUT /atsea/api/customer/{customerId}

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
**Returns:**
```
OK 200
{
    "customerId" : 0,
    "name"       : "Sally Vallery",
    "address"    : "my new address",
    "email"      : "sally@example.com".
    "phone"      : "phone as string"
    "username"   : "sallyv",
    "password"   : "betterpassword",
    "enabled"    : "true",
    "role"       : "USER"
}
```
**Error:**
```
HTTP 404 NOT FOUND
{
    "error":"Unable to update. Customer with id xxx not found"
}
```

## Delete a Customer
**Request:**
```
DELETE /atsea/api/customer/{customerId}

Host: localhost:8080
Auth: 
Content-type: application/json
Accept:  application/json
```
**Returns:**
```
HTTP 204 NO CONTENT
```
**Error:**
```
{
    "error":"Unable to delete. Customer with id 2 not found."
}
```
## Delete all Customers
**Request:**
```
DELETE /atsea/api/customer/

Host: localhost:8080
Auth: 
Content-type: application/json
Accept: application/json
```
**Returns:**
```
HTTP 204 NO CONTENT
```
# Order Requests

## Create an Order
**Request:**
```
POST /atsea/api/order/

Host: localhost:8080
Auth:
Content-type: application/json
Accept: application/json

{
    "orderId" : 1,
    "orderDate : "2017-02-28T19:52:39Z",
    "customerId" : "54321",
    "productsOrdered" : {"1":1, "2":1, "3":1}
}
```
**Returns:**
```
HTTP 201 CREATED
{
    "orderId": 1
}
```
**Error:**
```
HTTP 409 CONFLICT
{
    "error":"Unable to create. An order with id 1 already exists"
}
```
## Get All Orders
**Request:**
```
GET /atsea/api/order/

Host: localhost:8080
Auth: 
Content-type: application/json
Accept: application/json
```
**Returns:**
```
HTTP 200 OK

[
    {
        "orderId" : 1,
        "orderDate : "2017-02-28T19:52:39Z",
        "customerId" : "54321",
        "productsOrdered" : {"1":1,"2":1,"3":1}
    },
    {
        "orderId" : 2,
        "orderDate : "2017-02-28T19:52:39Z",
        "customerId" : "12345",
        "productsOrdered" : {"2":1,"3":1,"4":1}
    }
]
```
**Error:**
```
HTTP 404 NO CONTENT
```
#### Get Order by Id
**Request:**
```
GET /atsea/api/order/{orderId}

Host: localhost:8080
Auth: 
Content-type: application/json
Accept: application/json
```
**Returns:**
```
HTTP 200 OK

{
    "orderId" : 1,
    "orderDate : "2017-02-28T19:52:39Z",
    "customerId" : "54321",
    "productsOrdered" : {"1":1,"2":1,"3":1]
}
```
**Error:**
```
HTTP 404 NOT FOUND
{
    "error":"Order with id xx not found."
}
```
## Update an Order
**Request:**
```
POST: /atsea/api/order/{orderId}

Host: localhost:8080
Auth: 
Content-type: application/json
Accept: application/json

{
    "orderId" : "0", 
    "productsOrdered" : {"3":2, "6":3, "11":2},
     "orderDate" : "2017-02-28T19:52:39Z", 
     "customerId" : "8"
}
```
**Returns:**
```
HTTP 200 OK

{
    "customerId": 8,
    "orderDate": "2017-02-28T19:52:39Z",
    "orderId": 8,
    "productsOrdered": {"11": 1, "3": 1, "6": 1}
}
```
**Error:**
```
HTTP 404 NOT FOUND

{
    "error":"Unable to update. Order with id xx not found."
}
```
## Delete an Order
**Request:**
```
DELETE: /atsea/api/order/{orderId}

Host: localhost:8080
Auth:
Content-type: application/json
Accept: application/json
```
**Returns:**
```
HTTP 204 OK
```
**Error:**
```
HTTP 404 NOT FOUND

{
    "error":""Unable to delete order. Order with id xx not found."
}
```
# Login and Purchase

## Login
**Request:**
```
POST: /atsea/login/

Host: localhost:8080
Auth: 
Content-type: application/json
Accept: application/json

{
    "username" : "gordon",
    "password" : "gordonpassword"
}
```
**Returns:**
```
HTTP 200 OK

{
    "token" : "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzcGFjZWdvYXQiLCJyb2xlcyI6InNwYWNlZ29hdCIsImlhdCI6MTQ5MjA5ODAwM30.8zdJd5PyWmIEMkBcdtJlaT65nvRhKWH5QBSHjTLGQpo"
}
```
**Error:**
```
HTTP 403 UNAUTHORIZED

"Customer name or password not found."
```
#### Purchase
**Request:**
```
GET: /atsea/purchase/

Host: localhost:8080
Auth: 
Content-type: application/json
Accept: application/json


```
**Returns:**
```
HTTP 200 OK

{
    "message": "Thank you for shopping @Sea! We're sending a confirmation email shortly and getting your order ready!"
}
```
**Error:**

### System Utilities

#### Database Healthcheck
**Request:**
 ```
GET: /utility/healthcheck/
Host: localhost:8080
Auth: 
Content-type: application/json
Accept: application/json
```
**Returns:**
```
HTTP 200 OK
{
    "status":"2017-03-27 03:01"
}
 ```
**Error:**
```
HTTP 500 INTERNAL SERVER ERROR
{
    "error":"Database not responding."
}
```
 #### Get Container Id
**Request:**
```
GET: /utility/containerid/
Host: localhost:8080
Auth: 
Content-type: application/json
Accept: application/json
```
**Returns:**
```
HTTP 200 OK
{
    "host": "spara-mbp",
    "ip": "192.168.0.6"
}
```
**Error:**
```
HTTP 404 NOT FOUND
{
    "error":"Container id not found"
}

