### Class Endpoint
---


**Show All Classes**
----
  Returns list of all classes in the database/

* **URL**

  `/classes`

* **Method:**
  
  `GET` 
  
*  **URL Params**

   **Required:**
 
   `None`

   **Optional:**
 
   `None`

* **Data Params**

   `None`

* **Success Response:**

  * **Code:** `200` 
    **Content:** `None`
  ```
  {
  "_embedded": {
    "classes": [
      {
        "subject": "Object Oriented Design",
        "classNumber": "300",
        "description": null,
        "_links": {
          "self": {
            "href": "http://104.248.0.248/classes/1"
          },
          "klass": {
            "href": "http://104.248.0.248/classes/1"
          },
          "sessions": {
            "href": "http://104.248.0.248/classes/1/sessions"
          },
          "department": {
            "href": "http://104.248.0.248/classes/1/department"
          },
          "questions": {
            "href": "http://104.248.0.248/classes/1/questions"
          }
        }
      },
  ```
 
* **Error Response:**

  `None`

* **Sample Call:**

  ```
  curl -X GET \
  http://104.248.0.248/classes \
  -H 'Accept: */*' \
  -H 'Accept-Encoding: gzip, deflate' \
  -H 'Cache-Control: no-cache' \
  -H 'Connection: keep-alive' \
  -H 'Host: 104.248.0.248' \
  -H 'Postman-Token: 03cd37fd-beca-4517-b645-218a74e1e805,6fb6aaae-1c92-4046-957d-75cf3e439941' \
  -H 'User-Agent: PostmanRuntime/7.15.2' \
  -H 'cache-control: no-cache'
  ```



**Show Specific Class**
----
  Return JSON data for specific class. Identified by the class id

* **URL**

  `/classes/{class_id}`

* **Method:**
  
   `PUT`
  
*  **URL Params**
  
   **Required:**
 
   `None`

   **Optional:**
 
   `None`

* **Data Params**

  `None`

* **Success Response:**
  

  * **Code:** `200` 
    **Content:** 
    ```
      {
    "subject": "Object Oriented Design",
    "classNumber": "300",
    "description": null,
    "_links": {
      "self": {
        "href": "http://104.248.0.248/classes/1"
      },
      "klass": {
        "href": "http://104.248.0.248/classes/1"
      },
      "sessions": {
        "href": "http://104.248.0.248/classes/1/sessions"
      },
      "department": {
        "href": "http://104.248.0.248/classes/1/department"
      },
      "questions": {
        "href": "http://104.248.0.248/classes/1/questions"
      }
     }
    }
    ```
 
* **Error Response:**

  * **Code:** `404 NOT FOUND` 
    **Content:** `None`


* **Sample Call:**

  ```
  curl -X GET \
  http://104.248.0.248/classes/20 \
  -H 'Accept: */*' \
  -H 'Accept-Encoding: gzip, deflate' \
  -H 'Cache-Control: no-cache' \
  -H 'Connection: keep-alive' \
  -H 'Host: 104.248.0.248' \
  -H 'Postman-Token: 14068295-52a6-4371-b0ad-7bb865825a82,f98d4d27-2426-4b06-979b-ef44fcca2777' \
  -H 'User-Agent: PostmanRuntime/7.15.2' \
  -H 'cache-control: no-cache'
  ```

**Update Class**
----
  Updates an existing class. PUT executes a full replacement and PATCH only updates given sections

* **URL**

  `/classes/{class_id}`

* **Method:**

  `PUT` | `PATCH`
  
*  **URL Params**

   **Required:**
    `None`
   
   **Optional:**
 
   `None`

* **Data Params**
  `PUT`: Any missing fields will get a default value
  `PATCH`: Only include fields that are to be updated

  ```
  {
    "subject": "Object Oriented Design",
    "classNumber": "301",
    "description": "Updated Class description"
  }
  ```

* **Success Response:**
  
  <_What should the status code be on success and is there any returned data? This is useful when people need to to know what their callbacks should expect!_>

  * **Code:** `200` 
    **Content:** 
    ```
    {
    "subject": "Object Oriented Design",
    "classNumber": "301",
    "description": "Updated Class description",
    "_links": {
      "self": {
        "href": "http://104.248.0.248/classes/1"
      },
      "klass": {
        "href": "http://104.248.0.248/classes/1"
      },
      "sessions": {
        "href": "http://104.248.0.248/classes/1/sessions"
      },
      "department": {
        "href": "http://104.248.0.248/classes/1/department"
      },
      "questions": {
        "href": "http://104.248.0.248/classes/1/questions"
      }
    }
    ```
 
* **Error Response:**

  <_Most endpoints will have many ways they can fail. From unauthorized access, to wrongful parameters etc. All of those should be liste d here. It might seem repetitive, but it helps prevent assumptions from being made where they should be._>

  * **Code:** 4`06 NOT ACCEPTABLE` 
    **Content:** 
    ```
    {
      "timestamp": "2019-08-20T02:32:42.650+0000",
      "message": "Could not commit JPA transaction; nested exception is javax.persistence.RollbackException:          Error while committing the transaction"
    }
    ```


* **Sample Call:**

  ```
    curl -X PUT \
    http://104.248.0.248/classes/1 \
    -H 'Accept: */*' \
    -H 'Accept-Encoding: gzip, deflate' \
    -H 'Cache-Control: no-cache' \
    -H 'Connection: keep-alive' \
    -H 'Content-Length: 109' \
    -H 'Content-Type: application/json' \
    -H 'Host: 104.248.0.248' \
    -H 'Postman-Token: 126ec49b-bb80-469e-ae98-b6e2624453f4,74b089cc-994c-4d7f-8122-53adc5950f2c' \
    -H 'User-Agent: PostmanRuntime/7.15.2' \
    -H 'cache-control: no-cache' \
    -d '{
    "subject": "Object Oriented Design",
    "classNumber": "301",
    "description": "Updated Class description"
  }'
  ```


**Add ELO Question to Class**
----
 Return list of all comments

* **URL**

  `classes/{class_id}/addQuestion`

* **Method:**

  `POST` 
  
*  **URL Params**

   **Required:**
 
   `None`

   **Optional:**
 
   `None`

* **Data Params**

  `None`

* **Success Response:**
  

  * **Code:** `200` 
    **Content:** 
    ```
    {
      "question" : "Describe the System Software Specification (SSS)",
      "weekNumber" : "1"
    }
    ```
 
* **Error Response:**

  * **Code:** `406 NOT ACCEPTABLE`
    **Content:** 
    ```
    {
        "timestamp": "2019-08-20T02:38:45.345+0000",
        "message": "Class Not Found"
    }
    ```


* **Sample Call:**

  ```
    curl -X POST \
    http://104.248.0.248/classes/22/addQuestion \
    -H 'Accept: */*' \
    -H 'Accept-Encoding: gzip, deflate' \
    -H 'Cache-Control: no-cache' \
    -H 'Connection: keep-alive' \
    -H 'Content-Length: 91' \
    -H 'Content-Type: application/json' \
    -H 'Host: 104.248.0.248' \
    -H 'Postman-Token: bb106611-b92a-4806-86be-f2cb97b7aea9,22739b1b-34ca-4a3a-9259-53146fa89a1c' \
    -H 'User-Agent: PostmanRuntime/7.15.2' \
    -H 'cache-control: no-cache' \
    -d '{
    "question" : "Describe the System Software Specification (SSS)",
    "weekNumber" : "1"
  }

  ```


**Add a Session to a Class**
----

  Adds a new session to a class

* **URL**

  `classes/{class_id}/addSession`

* **Method:**
  
  <_The request type_>

  `POST`
  
*  **URL Params**

   **Required:**
 
   `None`

   **Optional:**
 
   `None`

* **Data Params**

  <_If making a post request, what should the body payload look like? URL Params rules apply here too._>

* **Success Response:**

  * **Code:** `200` 
    **Content:** 
    ```
    {
      "startDate"	:	"2019-01-15",
      "endDate"   :   "2019-02-15"
    }
    ```
* **Error Response:**

  <_Most endpoints will have many ways they can fail. From unauthorized access, to wrongful parameters etc. All of those should be liste d here. It might seem repetitive, but it helps prevent assumptions from being made where they should be._>

  * **Code:** `406 NOT ACCEPTABLE `
    **Content:** 
    ```
    {
        "timestamp": "2019-08-20T02:42:59.276+0000",
        "message": "Class Not Found"
    }
    ```


* **Sample Call:**

  ```
    curl -X POST \
    http://104.248.0.248/classes/17/addSession \
    -H 'Accept: */*' \
    -H 'Accept-Encoding: gzip, deflate' \
    -H 'Cache-Control: no-cache' \
    -H 'Connection: keep-alive' \
    -H 'Content-Length: 62' \
    -H 'Content-Type: application/json' \
    -H 'Host: 104.248.0.248' \
    -H 'Postman-Token: d97a5a99-7693-42b3-8e6d-719f566e3959,d840657b-8f6d-4465-970c-22260026cff6' \
    -H 'User-Agent: PostmanRuntime/7.15.2' \
    -H 'cache-control: no-cache' \
    -d '{
    "startDate"	:	"2019-01-15",
    "endDate"   :   "2019-02-15"
  }'
  ```

