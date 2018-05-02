## __Case Study:  myRetail RESTful service__

myRetail is a rapidly growing company with HQ in Richmond, VA and over 200 stores across the east coast. myRetail wants to make its internal data available to any number of client devices, from myRetail.com to native mobile apps. 

The goal for this exercise is to create an end-to-end Proof-of-Concept for a products API, which will aggregate product data from multiple sources and return it as JSON to the caller. 
Your goal is to create a RESTful service that can retrieve product and price details by ID. The URL structure is up to you to define, but try to follow some sort of logical convention.
Build an application that performs the following actions: 
Responds to an HTTP GET request at /products/{id} and delivers product data as JSON (where {id} will be a number. 

Example product IDs: 15117729, 16483589, 16696652, 16752456, 15643793) 
Example response: {"id":13860428,"name":"The Big Lebowski (Blu-ray) (Widescreen)","current_price":{"value": 13.49,"currency_code":"USD"}}

Performs an HTTP GET to retrieve the product name from an external API. (For this exercise the data will come from redsky.target.com, but let’s just pretend this is an internal resource hosted by myRetail) 

Example: http://redsky.target.com/v2/pdp/tcin/13860428?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics

Reads pricing information from a NoSQL data store and combines it with the product id and name from the HTTP request into a single response. 

BONUS: Accepts an HTTP PUT request at the same path (/products/{id}), containing a JSON request body similar to the GET response, and updates the product’s price in the data store. 

*********************************************************************************************************************************
## __Solution:__

###### __Technology Stack:__

1. Java 1.8
2. Spring Boot  
3. MongoDB(In memory)
4. Maven
5. Mokito/Junit
6. Swagger

###### __Setup instructions:__

Download project from the following git repository
https://github.com/MyPOCProjects/myRetail

a) Download as a ZIP file   OR

b) Clone the git project from git-bash or command prompt (You must have git setup)

--> Import the project into eclipse –   File->import


###### __Test the project:__

To run the test  Go to project folder and trigger following command on the command prompt ( or gitbash). 

mvn test

###### __To run the application:__

Go to the project folder and trigger the command on the command prompt:

mvn spring-boot:run 

###  Swagger2 documentation path

Below swagger documentation gives the complete details of the endpoint and can be tested easily.
http://localhost:8080/myretail/swagger-ui.html


# Calling myretail api services

1. Performing GET request on http://localhost:8080/myretail/products/13860428 will return json object with product information and pricing information.

GET http://localhost:8080/myretail/products/13860428

Response:-

{
  "id": 13860428,
  "name": "The Big Lebowski (Blu-ray)",
  "productPrice": {
    "price": "13.49",
    "currencyCode": "USD"
  }
}


2. To perform PUT operation, send JSON object with updated price in request body, it will be updated in the DB.

PUT http://localhost:8080/myretail/products/13860428

Request Body:-

{
  "id": 13860428,
  "name": "The Big Lebowski (Blu-ray)",
  "productPrice": {
    "price": "33.49",
    "currencyCode": "USD"
  }
}


3. To get all the products, use below resource.

GET http://localhost:8080/myretail/products

Response:- Returns all the products available in DB in JSON format


