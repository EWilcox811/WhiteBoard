### Sessions Endpoint
---


**Show all Sessions**
----
  Return list of all sessions.

* **URL**

  `/sessions`

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
        "sessions": [
          {
            "sessionName": "JAN_2019",
            "startDate": "2019-01-14",
            "endDate": "2019-02-14",
            "questionAndAnswers": [
              {
                "answers": 224,
                "numOfResponses": 3,
                "id": 8,
                "_links": {
                  "question": {
                    "href": "http://localhost:8080/eloquestion/5"
                  }
                }
              },
              {
                "answers": 0,
                "numOfResponses": 0,
                "id": 9,
                "_links": {
                  "question": {
                    "href": "http://localhost:8080/eloquestion/6"
                  }
                }
              },
    ```
 
* **Error Response:**

  `None`

* **Sample Call:**

  ```
    curl -X GET \
    http://localhost:8080/sessions \
    -H 'Accept: */*' \
    -H 'Accept-Encoding: gzip, deflate' \
    -H 'Cache-Control: no-cache' \
    -H 'Connection: keep-alive' \
    -H 'Host: localhost:8080' \
    -H 'Postman-Token: 93243b1f-8d37-460a-8158-bcc86729e917,e3343a6e-297d-4ecb-96fd-51f6ecbd6715' \
    -H 'User-Agent: PostmanRuntime/7.15.2' \
    -H 'cache-control: no-cache'
  ```


**Show One Session**
----

* **URL**

  `/sessions/{session_id}`

* **Method:**

  `GET`
  
*  **URL Params**

   **Required:**
 
   `session_id=[integer]`

   **Optional:**
 
   `None`

* **Data Params**

  `None`

* **Success Response:**
  

  * **Code:** 200 
    **Content:** 
    ```
        {
      "sessionName": "JAN_2019",
      "startDate": "2019-01-14",
      "endDate": "2019-02-14",
      "questionAndAnswers": [
        {
          "answers": 224,
          "numOfResponses": 3,
          "id": 8,
          "_links": {
            "question": {
              "href": "http://localhost:8080/eloquestion/5"
            }
          }
        },
        {
          "answers": 0,
          "numOfResponses": 0,
          "id": 9,
          "_links": {
            "question": {
              "href": "http://localhost:8080/eloquestion/6"
            }
          }
        },
        {
          "answers": 0,
          "numOfResponses": 0,
          "id": 10,
          "_links": {
            "question": {
              "href": "http://localhost:8080/eloquestion/7"
            }
          }
        }
      ],
      "id": 1,
      "_links": {
        "self": {
          "href": "http://localhost:8080/sessions/1"
        },
        "session": {
          "href": "http://localhost:8080/sessions/1"
        },
        "students": {
          "href": "http://localhost:8080/sessions/1/students"
        },
        "comments": {
          "href": "http://localhost:8080/sessions/1/comments"
        },
        "klass": {
          "href": "http://localhost:8080/sessions/1/klass"
        },
        "professors": {
          "href": "http://localhost:8080/sessions/1/professors"
        }
      }
    }
    ```
 
* **Error Response:**

  * **Code:** 404 NOT FOUND 
    **Content:** `None`


* **Sample Call:**

  ```
    curl -X GET \
    http://localhost:8080/sessions/5 \
    -H 'Accept: */*' \
    -H 'Accept-Encoding: gzip, deflate' \
    -H 'Cache-Control: no-cache' \
    -H 'Connection: keep-alive' \
    -H 'Host: localhost:8080' \
    -H 'Postman-Token: 01cd9776-8e49-4074-8981-63f026e7d93a,b9d4fc8b-4937-46da-a289-2f4ee3dfbeda' \
    -H 'User-Agent: PostmanRuntime/7.15.2' \
    -H 'cache-control: no-cache'
  ```


**Update Answers/Responces to Questions**
----
  Update the ELO questions with responces from the students. Each responce is added to the question total and the number of responces is incremented 

* **URL**

  `sessions/{session_id}/updateQuestion{question_id}`

* **Method:**
  
  <_The request type_>

 `POST`
  
*  **URL Params**

   **Required:**
 
   `session_id=[integer]`
   `question_id=[integer]`

* **Data Params**

  ```
  {
      "response": "50"
  }
  ```

* **Success Response:**


  * **Code:** `200` 
    **Content:** `None`
 
* **Error Response:**

  * **Code:** `406 NOT ACCEPTABLE`
    **Content:** 
    ```
    {
        "timestamp": "2019-08-21T05:01:34.560+0000",
        "message": "Question Id Not Found"
    }
    ```
  OR

  * **Code:** `406 NOT ACCEPTABLE`
    **Content:** 
    ```
    {
        "timestamp": "2019-08-21T05:01:34.560+0000",
        "message": "Session Id Not Found"
    }
    ```
* **Sample Call:**

  ```
    curl -X POST \
    http://localhost:8080/sessions/1000/updateQuestion/8 \
    -H 'Accept: */*' \
    -H 'Accept-Encoding: gzip, deflate' \
    -H 'Cache-Control: no-cache' \
    -H 'Connection: keep-alive' \
    -H 'Content-Length: 21' \
    -H 'Content-Type: application/json' \
    -H 'Host: localhost:8080' \
    -H 'Postman-Token: f4434055-ad46-4c54-97ac-a0b97691b754,4427494a-8edf-4d27-a432-24b6c0b646ee' \
    -H 'User-Agent: PostmanRuntime/7.15.2' \
    -H 'cache-control: no-cache' \
    -d '{
    "response": "50"
  }'
  ```

**Show Question Results for a Session**
----
  Return a list of ELO question results for a givin session.

* **URL**

  `sessions/{session_id}/results`

* **Method:**
  
  `GET` 
  
*  **URL Params**

   **Required:**
 
   `session_id=[integer]`

* **Data Params**

  `None`

* **Success Response:**
  


  * **Code:** `200` 
    **Content:**
    ```
        [
        {
            "id": "11",
            "question": "Describe the System Software Specification (SSS)",
            "average": "0",
            "totalResponses": "0"
        },
        {
            "id": "12",
            "question": "Develop an Operational Concept and Problem Description",
            "average": "0",
            "totalResponses": "0"
        },
        {
            "id": "13",
            "question": "Perform a High Level Process Decomposition",
            "average": "0",
            "totalResponses": "0"
        }
    ]
    ```
 
* **Error Response:**


  * **Code:** `406 NOT ACCEPTABLE`
    **Content:** 
    ```
    {
        "timestamp": "2019-08-21T23:54:24.261+0000",
        "message": "Session Id Not Found"
    }
    ```


* **Sample Call:**

  ```
      curl -X GET \
      http://localhost:8080/sessions/222/results \
      -H 'Accept: */*' \
      -H 'Accept-Encoding: gzip, deflate' \
      -H 'Cache-Control: no-cache' \
      -H 'Connection: keep-alive' \
      -H 'Content-Length: 21' \
      -H 'Content-Type: application/json' \
      -H 'Host: localhost:8080' \
      -H 'Postman-Token: 8e425766-6c11-4330-a1e7-c7ee457c11d6,3d643709-2a87-4a20-a868-dec4a88883e4' \
      -H 'User-Agent: PostmanRuntime/7.15.2' \
      -H 'cache-control: no-cache' \
      -d '{
      "response": "50"
    }'
  ```


**Add Student To Session**
----
  Add an existing to a session.

* **URL**

  `sessions/{session_id}/addStudent`

* **Method:**
  
 `POST` 
  
*  **URL Params**


   **Required:**
 
   `session_id=[integer]`


* **Data Params**

 ```
 {
	"user_Id" : "903"
 }
 ```
* **Success Response:**
  

  * **Code:** `200` 
    **Content:** `None`
 
* **Error Response:**
  * **Code:** `406 NOT ACCEPTABLE `
    **Content:** 
    ```
    {
        "timestamp": "2019-08-21T23:58:53.649+0000",
        "message": "Session Id Not Found"
    }
    ```
 OR
 
  * **Code:** `406 NOT ACCEPTABLE`
    **Content:**
    ```
    {
        "timestamp": "2019-08-21T23:59:49.486+0000",
        "message": "Student Id Not Found"
    }
    ```

* **Sample Call:**

  ```
      curl -X POST \
      http://localhost:8080/sessions/1/addStudent \
      -H 'Accept: */*' \
      -H 'Accept-Encoding: gzip, deflate' \
      -H 'Cache-Control: no-cache' \
      -H 'Connection: keep-alive' \
      -H 'Content-Length: 23' \
      -H 'Content-Type: application/json' \
      -H 'Host: localhost:8080' \
      -H 'Postman-Token: 2c34a8b1-10f2-4ae1-84f4-0efc2dc908d9,0331422f-51e8-4be1-8022-8e18c814ad2d' \
      -H 'User-Agent: PostmanRuntime/7.15.2' \
      -H 'cache-control: no-cache' \
      -d '{
      "user_Id" : "2"
    }'
  ```

**Add Existing Professor to a Sesssion**
----
  Add a Professor to a session.

* **URL**

  `sessions/{session_id}/addProfessor`

* **Method:**
  
 `POST` 
  
*  **URL Params**


   **Required:**
 
   `session_id=[integer]`


* **Data Params**

  ```
  {
    "user_Id" : "901"
  }
  ```

* **Success Response:**

  * **Code:** `200` 
    **Content:** `None`
 
* **Error Response:**

  * **Code:** `406 NOT ACCEPTABLE `
    **Content:** 
    ```
    {
        "timestamp": "2019-08-21T23:58:53.649+0000",
        "message": "Session Id Not Found"
    }
    ```
 OR
 
  * **Code:** `406 NOT ACCEPTABLE`
    **Content:**
    ```
    {
        "timestamp": "2019-08-21T23:59:49.486+0000",
        "message": "Professor Id Not Found"
    }
    ```

* **Sample Call:**

 ```
     curl -X POST \
      http://localhost:8080/sessions/1/addProfessor \
      -H 'Accept: */*' \
      -H 'Accept-Encoding: gzip, deflate' \
      -H 'Cache-Control: no-cache' \
      -H 'Connection: keep-alive' \
      -H 'Content-Length: 24' \
      -H 'Content-Type: application/json' \
      -H 'Host: localhost:8080' \
      -H 'Postman-Token: 216db2e5-6ce2-4675-a32c-d5c237a4911d,95d78206-694f-4129-9707-f04087d3c722' \
      -H 'User-Agent: PostmanRuntime/7.15.2' \
      -H 'cache-control: no-cache' \
      -d '{
      "user_Id" : "90111"
    }'
 ```


**Add Comment**
----
  Add a comment to a session.

* **URL**

  `sessions/{session_id}/addComment`

* **Method:**
  
 `POST` 
  
*  **URL Params**


   **Required:**
 
   `session_id=[integer]`

  
* **Data Params**
  isAnonymous takes a string. 1 = true, 0 = false
  
  ```
  {
    "userId" : "902",
    "message" : "This is a comment",
    "isAnonymous": "1"
  }
  ```
* **Success Response:**

  * **Code:** `406 NOT ACCEPTABLE `
    **Content:** 
    ```
    {
        "timestamp": "2019-08-21T23:58:53.649+0000",
        "message": "Session Id Not Found"
    }
    ```
 OR
 
  * **Code:** `406 NOT ACCEPTABLE`
    **Content:**
    ```
    {
        "timestamp": "2019-08-21T23:59:49.486+0000",
        "message": "User Id Not Found"
    }
    ```

* **Sample Call:**

  ```
      curl -X POST \
      http://localhost:8080/sessions/1/addComment \
      -H 'Accept: */*' \
      -H 'Accept-Encoding: gzip, deflate' \
      -H 'Cache-Control: no-cache' \
      -H 'Connection: keep-alive' \
      -H 'Content-Length: 43' \
      -H 'Content-Type: application/json' \
      -H 'Host: localhost:8080' \
      -H 'Postman-Token: 1e149e39-faab-48fb-b296-c469e58bfb2b,e7ab7b4e-9e24-41ad-8898-b9c626f1ef4d' \
      -H 'User-Agent: PostmanRuntime/7.15.2' \
      -H 'cache-control: no-cache' \
      -d '{
      "userId" : "902",
      "message" : "This is a comment",
      "isAnonymous": "1"
    }'
  ``` 


