### Learning Syles Endpoint
---


**Show Learning Style Question**
----
  Return list of all the learning style questions. All students use the same list of questions

* **URL**

  /learningstyle

* **Method:**

  `GET` 
  
*  **URL Params**

   None 
   
   **Required:**
 
   None

   **Optional:**
 
   None

* **Data Params**



* **Success Response:**
  
  * **Code:** 200 <br />
    **Content:** 
```
      {
      "_embedded": {
          "learningstyle": [
              {
                  "question": "When I learn I read books, articles, and handouts",
                  "_links": {
                      "self": {
                          "href": "http://localhost:8080/learningstyle/1"
                      },
                      "learningStyleQuestion": {
                          "href": "http://localhost:8080/learningstyle/1"
                      }
                  }
              },
              {
                  "question": "When I learn I use examples and applications",
                  "_links": {
                      "self": {
                          "href": "http://localhost:8080/learningstyle/2"
                      },
                      "learningStyleQuestion": {
                          "href": "http://localhost:8080/learningstyle/2"
                      }
                  }
              },
             ...
      "page": {
          "size": 20,
          "totalElements": 4,
          "totalPages": 1,
          "number": 0
      }
     }
```
 
* **Error Response:**

  No Error messages

* **Sample Call:**

  ```
  curl -X GET \
    http://localhost:8080/learningstyle \
    -H 'Accept: */*' \
    -H 'Cache-Control: no-cache' \
    -H 'Connection: keep-alive' \
    -H 'Content-Type: application/json' \
    -H 'Host: localhost:8080' \
    -H 'Postman-Token: 0b191b06-3c0f-428b-9cb2-729dd7912dc4,1adea678-8fda-4607-87a2-d333ff1e69b7' \
    -H 'User-Agent: PostmanRuntime/7.15.0' \
    -H 'accept-encoding: gzip, deflate' \
    -H 'cache-control: no-cache'
  ```


**Show Specific Learning Style Data**
----
  Returns data for specific learning style

* **URL**

  /learningstyle/{id}

* **Method:**
  
  `GET` 
  
*  **URL Params**

   **Required:**
 
   `id=[integer]`

   **Optional:**
 
   `None

* **Data Params**

  None

* **Success Response:**
  
  * **Code:** 200 <br />
    **Content:** 
    ```
    {
    "question": "When I learn I read books, articles, and handouts",
    "_links": {
        "self": {
            "href": "http://localhost:8080/learningstyle/1"
        },
        "learningStyleQuestion": {
            "href": "http://localhost:8080/learningstyle/1"
        }
    }
  }
    ```
 
* **Error Response:**


  * **Code:** 404 NOT FOUND <br />
    **Content:** None
    
* **Sample Call:**
```
curl -X GET \
  http://localhost:8080/learningstyle/9 \
  -H 'Accept: */*' \
  -H 'Cache-Control: no-cache' \
  -H 'Connection: keep-alive' \
  -H 'Content-Type: application/json' \
  -H 'Host: localhost:8080' \
  -H 'Postman-Token: de09e23e-7462-48fb-bb3c-979e54880eb5,cc35f737-abd3-4cfa-a693-6f87578984e2' \
  -H 'User-Agent: PostmanRuntime/7.15.0' \
  -H 'accept-encoding: gzip, deflate' \
  -H 'cache-control: no-cache'
```


**Add Learning Style Question**
----
  Adds a new learning style too the list of questions.

* **URL**

  /learningstyle/add

* **Method:**

   `POST` 
  
*  **URL Params**

   **Required:**
 
   None

   **Optional:**
 
   None

* **Data Params**

  ```
  {
	"question" : "this is a new learning style question"
  }
  ```

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** None
 
* **Error Response:**
  None

* **Sample Call:**

  ```
  curl -X POST \
    http://localhost:8080/learningstyle/add \
    -H 'Accept: */*' \
    -H 'Cache-Control: no-cache' \
    -H 'Connection: keep-alive' \
    -H 'Content-Type: application/json' \
    -H 'Host: localhost:8080' \
    -H 'Postman-Token: 6f503d19-de37-406d-969a-f82203295129,f220ab05-9eb4-4ce7-a994-a9419219773d' \
    -H 'User-Agent: PostmanRuntime/7.15.0' \
    -H 'accept-encoding: gzip, deflate' \
    -H 'cache-control: no-cache' \
    -H 'content-length: 5' \
    -d '{
    "question" : "this is a new learning style question"
  }'
  ```


