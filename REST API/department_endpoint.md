### Department Endpoint
---


**Show All Departments**
----
  Returns list of all departments

* **URL**

  `/departments`

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
    **Content:** 
    ```
    {
      "_embedded": {
        "departments": [
          {
            "departmentAbbreviation": "CSC",
            "departmentName": "Computer Science",
            "_links": {
              "self": {
                "href": "http://104.248.0.248/departments/1"
              },
              "department": {
                "href": "http://104.248.0.248/departments/1"
              },
              "departmentClasses": {
                "href": "http://104.248.0.248/departments/1/departmentClasses"
              }
            }
          }
        ]
      }, ...
    ```
 
* **Error Response:**

 `None`

* **Sample Call:**

   ```
      curl -X GET \
    http://104.248.0.248/departments \
    -H 'Accept: */*' \
    -H 'Accept-Encoding: gzip, deflate' \
    -H 'Cache-Control: no-cache' \
    -H 'Connection: keep-alive' \
    -H 'Host: 104.248.0.248' \
    -H 'Postman-Token: f399b139-d162-4f6e-a77b-60941c2fc309,9f6aaad5-ac7a-4e9b-902a-5602293ae6f3' \
    -H 'User-Agent: PostmanRuntime/7.15.2' \
    -H 'cache-control: no-cache'
   ```


**Get Specific Department**
----
  Returns one department.

* **URL**

 `/departments/{department_id}`

* **Method:**
  
  `GET` 
  
*  **URL Params**

   **Required:**
 
   `department_id=[integer]`

   **Optional:**
 
   `None`

* **Data Params**

  `None`

* **Success Response:**
  

  * **Code:** `200` 
    **Content:** 
    ```
    {
      "departmentAbbreviation": "CSC",
      "departmentName": "Computer Science",
      "_links": {
        "self": {
          "href": "http://104.248.0.248/departments/1"
        },
        "department": {
          "href": "http://104.248.0.248/departments/1"
        },
        "departmentClasses": {
          "href": "http://104.248.0.248/departments/1/departmentClasses"
        }
      }
    }
    ```
 
* **Error Response:**

  <_Most endpoints will have many ways they can fail. From unauthorized access, to wrongful parameters etc. All of those should be liste d here. It might seem repetitive, but it helps prevent assumptions from being made where they should be._>

  * **Code:** 404 NOT FOUND <br />
    **Content:** `None`


* **Sample Call:**

  ```
    curl -X GET \
    http://104.248.0.248/departments/9 \
    -H 'Accept: */*' \
    -H 'Accept-Encoding: gzip, deflate' \
    -H 'Cache-Control: no-cache' \
    -H 'Connection: keep-alive' \
    -H 'Host: 104.248.0.248' \
    -H 'Postman-Token: 7359516d-91d1-4ce8-8d9e-02d8002392e7,11a2f701-17b1-4ace-b491-65dc735425df' \
    -H 'User-Agent: PostmanRuntime/7.15.2' \
    -H 'cache-control: no-cache'
  ```


**Update Department**
----
    Updates an existing department. 

* **URL**

  `/departments/{department_id}`

* **Method:**
 `PATCH` | `PUT`
  
*  **URL Params**


   **Required:**
 
   `department_id=[integer]`

   **Optional:**
 
   `None`

* **Data Params**

  

* **Success Response:**
  

  * **Code:** `200`
    **Content:** 
    ```
    {
      "departmentAbbreviation": "CSC",
      "departmentName": "Computer Science1",
      "_links": {
        "self": {
          "href": "http://104.248.0.248/departments/1"
        },
        "department": {
          "href": "http://104.248.0.248/departments/1"
        },
        "departmentClasses": {
          "href": "http://104.248.0.248/departments/1/departmentClasses"
        }
      }
    }
    ```
 
* **Error Response:**


  * **Code:** `406 NOT ACCEPTABLE `
    **Content:** `None`


* **Sample Call:**

  ```
      curl -X PUT \
      http://104.248.0.248/departments/1 \
      -H 'Accept: */*' \
      -H 'Accept-Encoding: gzip, deflate' \
      -H 'Cache-Control: no-cache' \
      -H 'Connection: keep-alive' \
      -H 'Content-Length: 77' \
      -H 'Content-Type: application/json' \
      -H 'Host: 104.248.0.248' \
      -H 'Postman-Token: 1e83bad1-5c1b-47d6-8c50-391827b4bf19,4b489e0a-3040-4a5d-9f2a-35878b410999' \
      -H 'User-Agent: PostmanRuntime/7.15.2' \
      -H 'cache-control: no-cache' \
      -d '{
      "departmentAbbreviation": "CSC",
      "departmentName": "Computer Science2"
    }'
  ```


**Add Class to Department**
----
  Add a new class to a Department

* **URL**

  `departments/{department_id}/addClass`

* **Method:**
  
  `POST` 
  
*  **URL Params**

   **Required:**
 
   `department_id=[integer]`

   **Optional:**
 
   `None`

* **Data Params**

  ```
  {
      "subject" : "Database Design",
      "classNumber" : "422",
      "description" : "A survey of principles, structure, analysis, ...
    }
  ```

* **Success Response:**

  * **Code:** `200`
    **Content:** `None`
 
* **Error Response:**


  * **Code:** `406 NOT ACCEPTABLE` 
    **Content:** `None'
  

* **Sample Call:**
  ```
      curl -X POST \
      http://localhost:8080/departments/1/addClass \
      -H 'Accept: */*' \
      -H 'Accept-Encoding: gzip, deflate' \
      -H 'Cache-Control: no-cache' \
      -H 'Connection: keep-alive' \
      -H 'Content-Length: 60' \
      -H 'Content-Type: application/json' \
      -H 'Host: localhost:8080' \
      -H 'Postman-Token: 17f6eb05-24c4-4f68-9579-a8876f08d1f8,8dfe5da2-4d3a-474a-96a2-678dfcc0143a' \
      -H 'User-Agent: PostmanRuntime/7.15.2' \
      -H 'cache-control: no-cache' \
      -d '{
      "subject" : "Database Design",
      "classNumber" : "422",
      "description" : "A survey of principles, structure, analysis, and techniques of database design and implementation. Topics include physical and logical design, normalization, database models, security, integrity and queries."
    }'
  ```

