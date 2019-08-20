### Comment Endpoint
---


**Show All Comments**
----
  Return list of all comments

* **URL**

  `/comments`

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
        "comments": [
          {
            "dateCreated": "2019-08-20T03:38:56.000+0000",
            "userName": "JMcCabe902",
            "message": "This is a comment",
            "parentId": 1,
            "numOfRelies": 1,
            "isAnonymous": "1",
            "id": 1,
            "_links": {
              "self": {
                "href": "http://localhost:8080/comments/1"
              },
              "comment": {
                "href": "http://localhost:8080/comments/1"
              },
              "session": {
                "href": "http://localhost:8080/comments/1/session"
              },
              "parentComment": {
                "href": "http://localhost:8080/comments/1/parentComment"
              },
              "user": {
                "href": "http://localhost:8080/comments/1/user"
              },
              "replies": {
                "href": "http://localhost:8080/comments/1/replies"
              }
            }
          }, ...
    ```
* **Error Response:**

  `None`

* **Sample Call:**

  ```
    curl -X GET \
    http://localhost:8080/comments \
    -H 'Accept: */*' \
    -H 'Accept-Encoding: gzip, deflate' \
    -H 'Cache-Control: no-cache' \
    -H 'Connection: keep-alive' \
    -H 'Host: localhost:8080' \
    -H 'Postman-Token: 3b754113-6097-4282-a782-212608f2123a,f092016e-269e-458b-abb4-66ed403ef0a7' \
    -H 'User-Agent: PostmanRuntime/7.15.2' \
    -H 'cache-control: no-cache'
  ```


**Get Single Comment**
----
  Return a single comment

* **URL**

 `/comments/{comment_id}`

* **Method:**
  
  `GET` 
  
*  **URL Params**


   **Required:**
 
   `comment_id=[integer]`

   **Optional:**
 
   `None`

* **Data Params**

 `None`
* **Success Response:**


  * **Code:** `200` 
    **Content:** 
    ```
    {
      "dateCreated": "2019-08-20T03:38:56.000+0000",
      "userName": "JMcCabe902",
      "message": "This is a comment",
      "parentId": 1,
      "numOfRelies": 1,
      "isAnonymous": "1",
      "id": 1,
      "_links": {
        "self": {
          "href": "http://localhost:8080/comments/1"
        },
        "comment": {
          "href": "http://localhost:8080/comments/1"
        },
        "session": {
          "href": "http://localhost:8080/comments/1/session"
        },
        "parentComment": {
          "href": "http://localhost:8080/comments/1/parentComment"
        },
        "user": {
          "href": "http://localhost:8080/comments/1/user"
        },
        "replies": {
          "href": "http://localhost:8080/comments/1/replies"
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
  http://localhost:8080/comments/1 \
  -H 'Accept: */*' \
  -H 'Accept-Encoding: gzip, deflate' \
  -H 'Cache-Control: no-cache' \
  -H 'Connection: keep-alive' \
  -H 'Host: localhost:8080' \
  -H 'Postman-Token: ba4f65e7-51d1-4dff-94b8-b314c3d66b12,ac7d4d0d-b87d-47b8-9882-2edcabcb8635' \
  -H 'User-Agent: PostmanRuntime/7.15.2' \
  -H 'cache-control: no-cache'
  ```


**Get all Replies for a Comment**
----
  Return a list of all the replies to a given comment

* **URL**

  `/comment/{comment_id}/replies`

* **Method:**
  
  `GET`
  
*  **URL Params**


   **Required:**
 
   `comment_id=[integer]`

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
        "comments": [
          {
            "dateCreated": "2019-08-20T03:38:56.000+0000",
            "userName": null,
            "message": "this the the first reply...",
            "parentId": 1,
            "numOfRelies": 0,
            "isAnonymous": null,
            "id": 4,
            "_links": {
              "self": {
                "href": "http://localhost:8080/comments/4"
              },
              "comment": {
                "href": "http://localhost:8080/comments/4"
              },
              "session": {
                "href": "http://localhost:8080/comments/4/session"
              },
              "parentComment": {
                "href": "http://localhost:8080/comments/4/parentComment"
              },
              "user": {
                "href": "http://localhost:8080/comments/4/user"
              },
              "replies": {
                "href": "http://localhost:8080/comments/4/replies"
              }
            }
          }
        ]
      },
      "_links": {
        "self": {
          "href": "http://localhost:8080/comments/1/replies"
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
    http://localhost:8080/comments/1/replies \
    -H 'Accept: */*' \
    -H 'Accept-Encoding: gzip, deflate' \
    -H 'Cache-Control: no-cache' \
    -H 'Connection: keep-alive' \
    -H 'Host: localhost:8080' \
    -H 'Postman-Token: 7a1bd0d7-9682-4906-b460-9364f0bbb665,18c00db9-a23b-4d2e-a498-450f74f53150' \
    -H 'User-Agent: PostmanRuntime/7.15.2' \
    -H 'cache-control: no-cache'
  ```


**Add Reply**
----
  Add a reply to a given comment

* **URL**

  `comments/{comment_id}/addReply`

* **Method:**
  
  `POST` 
  
*  **URL Params**


   **Required:**
 
   `comment_id=[integer]`

   **Optional:**
 
   `None`

* **Data Params**

  ```
  {
    "userId" : "903",
    "message" : "this the the first reply..."
  }
  ```
* **Success Response:**
  

  * **Code:** `200` 
    **Content:** `None`
 
* **Error Response:**


  * **Code:** 406 NOT ACCEPTABLE <br />
    **Content:** 
    ```
    {
        "timestamp": "2019-08-20T03:55:48.890+0000",
        "message": "Parent Comment Not Found"
    }
    ```


* **Sample Call:**

  ```
      curl -X POST \
      http://localhost:8080/comments/9/addReply \
      -H 'Accept: */*' \
      -H 'Accept-Encoding: gzip, deflate' \
      -H 'Cache-Control: no-cache' \
      -H 'Connection: keep-alive' \
      -H 'Content-Length: 65' \
      -H 'Content-Type: application/json' \
      -H 'Host: localhost:8080' \
      -H 'Postman-Token: 5f7d59bf-e6f6-4a00-b9d8-4ccf2d2599bb,03dc7d48-dac7-4cfc-a949-70bd50c478b2' \
      -H 'User-Agent: PostmanRuntime/7.15.2' \
      -H 'cache-control: no-cache' \
      -d '{
      "userId" : "903",
      "message" : "this the the first reply..."
    }'
  ```
