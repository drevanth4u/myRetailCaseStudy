# myRetailApp CaseStudy

myRetail is a rapidly growing company with HQ in Richmond, VA and over 200 stores across the east coast. myRetail wants to make its internal data available to any number of client devices, from myRetail.com to native mobile apps. 

The goal for this exercise is to create an end-to-end Proof-of-Concept for a products API, which will aggregate product data from multiple sources and return it as JSON to the caller. 
Your goal is to create a RESTful service that can retrieve product and price details by ID. The URL structure is up to you to define but try to follow some sort of logical convention.
Build an application that performs the following actions: 

• Responds to an HTTP GET request at /products/{id} and delivers product data as JSON (where {id} will be a number.

Example product IDs: 13860428, 54456119, 13264003, 12954218) 
•	Example response: {"id":13860428,"name":"The Big Lebowski (Blu-ray) (Widescreen)","current_price":{"value": 13.49,"currency_code":"USD"}}

•	Performs an HTTP GET to retrieve the product name from an external API. (For this exercise, the data will come from redsky.target.com, but let’s just pretend this is an internal resource hosted by myRetail)

•	Example: https://redsky.target.com/v3/pdp/tcin/13860428?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics&key=candidate 

•	Reads pricing information from a NoSQL data store and combines it with the product id and name from the HTTP request into a single response. 

•	BONUS: Accepts an HTTP PUT request at the same path (/products/{id}), containing a JSON request body similar to the GET response, and updates the product’s price in the data store.  

# __Implementation:__

## __myRetail API Solution provides the ability to:__

<ol>
  <li>Get product and price information by Product Id.</li>
  <li>Update the prouct price information in the database.</li>
  <li>Secure API with basic authentication.</li>	
  <li>Implement Swagger2 for API documentation</li>
</ol>
All the end points are totally secure in this application. I have implemented basic security and method level security as well. Update resource can be accessed by admin/admin user only.

        Method               Request                   Credentials
        GET              /products/{id}              [SECURE -- user/password]
        PUT              /products/{id}              [SECURE -- user /password]

###### __Technology Stack:__

1. Spring Boot : 
	https://start.spring.io/
	https://spring.io/guides/gs/serving-web-content/ 
2. Feign:
Declarative REST Client: Feign creates a dynamic implementation of an interface decorated with JAX-RS or Spring MVC annotations.
	https://cloud.spring.io/spring-cloud-netflix/ 
3. MongoDB:
	https://www.mongodb.com/what-is-mongodb 
4. Maven:
	https://maven.apache.org/ 
5. Mokito/Junit:
	http://site.mockito.org/ 
6. Postman: 
	https://chrome.google.com/webstore/detail/postman/fhbjgbiflinjbdggehcddcbncdddomop?hl=en 

###### __Setup instructions:__

1. Java 1.8
2. Eclipse IDE for Enterprise Java and Web Developers (includes Incubating components)
Version: 2021-03 (4.19.0)   https://www.eclipse.org/
3. Install Mongo DB: https://docs.mongodb.com/manual/tutorial/install-mongodb-on-windows/
4. Maven: https://www.mkyong.com/maven/how-to-install-maven-in-windows/ 
5. Github:
Download project from the following Git repository
https://github.com/drevanth4u/myRetailCaseStudy
Download as a ZIP file 
6. Import the project into eclipse –   File->import -> Maven Project

###### __Test the project:__

Test cases are present on the following directory. I have written some test cases for controller class and service  class using mokito. I am using mokito for mockdata.

myRetail\src\test\java

###### __To run the application:__

Run mongo DB from the command prompt.  And test  ---  http://localhost:27017/  (default port)
Go to the project folder and trigger the command:

mvn spring-boot:run 

###### __Check the http Request:__

### Secure API
The end point of this application is fully secure.
1. user/password  --  get product by prodctId.

2. user/password   --- To update product price information.

### Swagger2 documentation path

http://localhost:8085/swagger-ui.html

![Sawgger_UI](https://user-images.githubusercontent.com/46389696/118859456-de128a80-b89f-11eb-8385-0060a55422a0.png)


GET Product Info: With valid product id and credentials (http://localhost:8080/products/13264003)

![Get_Request_1](https://user-images.githubusercontent.com/46389696/118859565-fda9b300-b89f-11eb-8a26-c606be9f2382.png)


GET Product Info: With wrong product id and valid credentials(http://localhost:8080/products/123)

![Get_Request_2](https://user-images.githubusercontent.com/46389696/118859626-1023ec80-b8a0-11eb-99cd-41b18ec3cade.png)


GET Product Info: (Fallback) With valid product id and credentials and External API is down (http://localhost:8080/products/13264003)

![Get_Request_3](https://user-images.githubusercontent.com/46389696/118859829-49f4f300-b8a0-11eb-817d-133dca76474c.png)


PUT: Update Product price with valid credentials (http://localhost:8085/products/13264003)

![Put_Request_1](https://user-images.githubusercontent.com/46389696/118859929-698c1b80-b8a0-11eb-984f-df6aac2a276f.png)


PUT: Update Product price with wrong product id and valid credentials (http://localhost:8085/products/1360428)

![Put_Request_2](https://user-images.githubusercontent.com/46389696/118860059-8cb6cb00-b8a0-11eb-9735-77ac469f616d.png)


PUT: Update Product price with missing product id, price, and valid credentials (http://localhost:8085/products/1360428)

![Put_Request_3](https://user-images.githubusercontent.com/46389696/118860133-a48e4f00-b8a0-11eb-81b6-290e2e5ccaf9.png)













