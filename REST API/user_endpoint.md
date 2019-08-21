### Users Endpoints
---


**Show All Users**
----
  Returns JSON data listing all users in the system.

* **URL**

  `/users`

* **Method:**
  
  `GET` 
  
*  **URL Params**

   `None`

   **Required:**
 
   `None`

   **Optional:**
 
   `{?page,size,sort}`

* **Data Params**

  `None`

* **Success Response:**

  * **Code:** `200` 
    **Content:** 
    ```
    _embedded": {
        "users": [
            {
                "username": "admin",
                "_links": {
                    "self": {
                        "href": "http://localhost:8080/users/900"
                    },
                    "user": {
                        "href": "http://localhost:8080/users/900"
                    },
                    "userData": {
                        "href": "http://localhost:8080/users/900/userData"
                    },
                    "roles": {
                        "href": "http://localhost:8080/users/900/roles"
                    }
                }
            }, ...
    ```
 
* **Error Response:**

  `None`

* **Sample Call:**

  ````
  curl -X GET \
    http://localhost:8080/users \
    -H 'Accept: */*' \
    -H 'Cache-Control: no-cache' \
    -H 'Connection: keep-alive' \
    -H 'Host: localhost:8080' \
    -H 'Postman-Token: 9cc91996-79c9-45f1-8a8a-b0876fbe9a55,2ed1dcad-9640-4daa-9bb9-791cefeb40ef' \
    -H 'User-Agent: PostmanRuntime/7.15.0' \
    -H 'accept-encoding: gzip, deflate' \
    -H 'cache-control: no-cache'
  ````


**Show User**
----
  Returns JSON data representing one user.

* **URL**

  `/users/{user_id}`

* **Method:**
  
  `GET` 
  
*  **URL Params**

   **Required:**
 
   `user_id=[integer]`

* **Data Params**

  None

* **Success Response:**

  * **Code:** `200` 
    **Content:** 
    ```
    {
    "username": "JDesire901",
    "_links": {
        "self": {
            "href": "http://localhost:8080/users/901"
        },
        "user": {
            "href": "http://localhost:8080/users/901"
        },
        "userData": {
            "href": "http://localhost:8080/users/901/userData"
        },
        "roles": {
            "href": "http://localhost:8080/users/901/roles"
        }
    }
    }
    ```
 
* **Error Response:**

  * **Code:** `404 NOT FOUND` 
    **Content:** None


* **Sample Call:**

  ```
  curl -X GET \
    http://localhost:8080/users/901 \
    -H 'Accept: */*' \
    -H 'Cache-Control: no-cache' \
    -H 'Connection: keep-alive' \
    -H 'Host: localhost:8080' \
    -H 'Postman-Token: 037078e7-6cbd-4850-a72f-b9f4ae7a860b,c570161b-bd8f-4a9e-a508-d70db298b34f' \
    -H 'User-Agent: PostmanRuntime/7.15.0' \
    -H 'accept-encoding: gzip, deflate' \
    -H 'cache-control: no-cache'
    ```
  

**Signin**
----
  Allows users to sign into the system.

* **URL**

  `/user/signin`

* **Method:**
  
 NOTE: pause here to fix adding students/professors to classes

  'POST` 
  
*  **URL Params**

   **Required:**
 
   `None`

   **Optional:**
 
   `None`

* **Data Params**

  ```
  {
    "username" : "MCoopman902",
    "password" : "password"
  }
  ```

* **Success Response:**


  * **Code:** `200`
    **Content:** 
    ```
    {
      "username": "MCoopman902",
      "userId": 902,
      "jwtToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJNQ29vcG1hbjkwMiIsInJvbGVzIjpbeyJhdXRob3JpdHkiOiJST0xFX1VTRVIifV0sImlhdCI6MTU2NDkzOTQzMiwiZXhwIjoxNTY3NTMxNDMyfQ.RN4CKNDS8_2EHtI9kG0YkejOOJ4iTYmt8S-YiaTU77s",
      "userType": "students",
      "currentDepartmentId": 5,
      "currentSessionNumberId": 3,
      "currentClassNumberId": 3
    }
    ```
 
* **Error Response:**

  * **Code:** `406 Not Acceptable` 
    **Content:** 
    ```
      {
        "timestamp": "2019-08-09T02:43:00.430+0000",
        "message": "Invalid Username"
      }
    ```
  OR
    
  * **Code:** `406 Not Acceptable` 
    **Content:** 
    ```
      {
        "timestamp": "2019-08-09T02:43:35.722+0000",
        "message": "Invalid Password"
      }
    ```
  OR
    
  * **Code:** `406 Not Acceptable` <br />
    **Content:** 
    ```
       {
        "timestamp": "2019-08-09T02:43:35.722+0000",
         message": "Missing Field"
       }
      ```

* **Sample Call:**

  ```
  curl -X POST \
    http://localhost:8080/users/signin \
    -H 'Accept: */*' \
    -H 'Cache-Control: no-cache' \
    -H 'Connection: keep-alive' \
    -H 'Content-Type: application/json' \
    -H 'Host: localhost:8080' \
    -H 'Postman-Token: a0e512b2-7386-4803-ad0f-e98b9f488192,01a3b16b-9a9d-458c-91ee-867f8982a54a' \
    -H 'User-Agent: PostmanRuntime/7.15.0' \
    -H 'accept-encoding: gzip, deflate' \
    -H 'cache-control: no-cache' \
    -H 'content-length: 29' \
    -d '{
    "username" : "MCoopman902",
    "password" : "password"
  }'
  ```


**Add New Student**
----
  Adds a student into the system. Attaches the user privileges.

* **URL**

  `/user/addStudent`

* **Method:**
  
  `POST` 
  
*  **URL Params**

   **Required:**
 
   `None`

   **Optional:**
 
   `None`

* **Data Params**

   ```
   {
      "firstName" : "Mike",
      "lastName"  : "Coopman",
      "password"  : "password",
      "email"     : "macoopman@gmail.com",
      "gpa"       : "3.95",
      "major"     : "Computer Science"
  }
   ```

* **Success Response:**
  

  * **Code:** `200` 
    **Content:** 
    ```
    {
    "id": 905,
    "username": "EWilcox905",
    "roles": [
        {
            "id": 2,
            "description": "User role",
            "roleName": "ROLE_USER",
            "authority": "ROLE_USER"
        }
    ],
    "userData": {
        "id": 905,
        "firstName": "Evan",
        "lastName": "Wilcox",
        "email": "ewilcox@gmail.com",
        "dateJoined": "2019-08-04T17:59:56.330+0000",
        "user": null,
        "currentSession": null,
        "questions": null,
        "gpa": 3.95,
        "major": "Computer Science",
        "learningStyleAnswersList": [
            {
                "question": {
                    "id": 1,
                    "question": "When I learn I read books, articles, and handouts"
                },
                "answers": 0,
                "id": 9
            },
            {
                "question": {
                    "id": 2,
                    "question": "When I learn I use examples and applications"
                },
                "answers": 0,
                "id": 10
            },
            {
                "question": {
                    "id": 3,
                    "question": "When I learn I see patterns in things"
                },
                "answers": 0,
                "id": 11
            },
            {
                "question": {
                    "id": 4,
                    "question": "When I learn I like to talk things through"
                },
                "answers": 0,
                "id": 12
            }
        ]
      }
    }
    ```
 
* **Error Response:**

  * **Code:** `406 Not Acceptable `
    **Content:** 
    ```
    {
      "timestamp": "2019-08-09T02:45:01.615+0000",
      "message": "Email Address Already Exists
    }
    ```
   


* **Sample Call:**

  ```
  curl -X POST \
    http://localhost:8080/users/addStudent \
    -H 'Accept: */*' \
    -H 'Cache-Control: no-cache' \
    -H 'Connection: keep-alive' \
    -H 'Content-Type: application/json' \
    -H 'Host: localhost:8080' \
    -H 'Postman-Token: 584b0245-5afa-4d69-889c-48060afc35ec,ff387f9d-1a39-44a1-bae2-eba3eddd1eed' \
    -H 'User-Agent: PostmanRuntime/7.15.0' \
    -H 'accept-encoding: gzip, deflate' \
    -H 'cache-control: no-cache' \
    -H 'content-length: 91' \
    -d '{
    "firstName" : "Evan",
    "lastName"  : "Wilcox",
    "password"  : "password",
    "email"     : "ewilcox@gmail.com",
    "gpa"       : "3.95",
    "major"     : "Computer Science"
  }
  ```

* **Notes:**

  If any information is not available for the user being created keep the field in the request and provide a blank string as the value.
  


**Add Admin User**
----
  Adds an admininstrator account to the system. Attaches the admin privileges.

* **URL**

  `/users/addAdmin`

* **Method:**
  
 `POST` 
  
*  **URL Params**

   **Required:**
 
   `None`

   **Optional:**
 
   `None`

* **Data Params**

  ```
  {
    "firstName" : "Admin",
    "lastName"  : "Admin",
    "password"  : "admin",
    "email"     : "admin@admin.com"
  }
  ```

* **Success Response:**
  * **Code:** `200` 
    **Content:** 
    ```
    {
    "id": 901,
    "username": "AAdmin901",
    "roles": [
        {
            "id": 1,
            "description": "Admin role",
            "roleName": "ROLE_ADMIN",
            "authority": "ROLE_ADMIN"
        }
    ],
    "userData": {
        "id": 901,
        "firstName": "Admin",
        "lastName": "Admin",
        "email": "admin@admin.com",
        "dateJoined": "2019-08-04T18:10:12.198+0000",
        "user": null,
        "currentSession": null,
        "questions": null
      }
    }
    
    ```
 
* **Error Response:**

   * **Code:** `406 Not Acceptable `
    **Content:** 
    ```
    {
      "timestamp": "2019-08-09T02:45:01.615+0000",
      "message": "Email Address Already Exists
    }
    ```

* **Sample Call:**

  ```
  curl -X POST \
    http://localhost:8080/users/addAdmin \
    -H 'Accept: */*' \
    -H 'Cache-Control: no-cache' \
    -H 'Connection: keep-alive' \
    -H 'Content-Type: application/json' \
    -H 'Host: localhost:8080' \
    -H 'Postman-Token: eb8d522d-a564-4e23-bbcd-a992d5d20b62,abe27b93-9229-4e3f-943d-fbe6c110fa4d' \
    -H 'User-Agent: PostmanRuntime/7.15.0' \
    -H 'accept-encoding: gzip, deflate' \
    -H 'cache-control: no-cache' \
    -H 'content-length: 108' \
    -d '{
    "firstName" : "Admin",
    "lastName"  : "Admin",
    "password"  : "admin",
    "email"     : "admin@admin.com"
  }'
  ```
* **Notes:**

  If any information is not available for the user being created keep the field in the request and provide a blank string as the value.


**Add New Professor**
---
  Adds a professor to the system. Attaches the user privileges.

* **URL**

  `/users/addProfessor`

* **Method:**

  `POST` 
  
*  **URL Params**

   **Required:**
 
    `None`

   **Optional:**
 
   `None`

* **Data Params**

  ```
  {
    "firstName" : "Jean",
    "lastName"  : "Desire",
    "password"  : "password",
    "email"     : "jean.desire@natuniv.edu",
    "rating"       : "3.95"
  }
  ```

* **Success Response:**
  
  * **Code:** `200` 
    **Content:** 
    ```
    {
    "id": 902,
    "username": "JDesire902",
    "roles": [
        {
            "id": 2,
            "description": "User role",
            "roleName": "ROLE_USER",
            "authority": "ROLE_USER"
        }
    ],
    "userData": {
        "id": 902,
        "firstName": "Jean",
        "lastName": "Desire",
        "email": "jean.desire@natuniv.edu",
        "dateJoined": "2019-08-04T18:14:13.538+0000",
        "user": null,
        "currentSession": null,
        "questions": null,
        "rating": 3.95
        }
    }
    ```
 
* **Error Response:**


  * **Code:** `406 Not Acceptable `
    **Content:** 
    ```
    {
    "timestamp": "2019-08-09T02:45:01.615+0000",
    "message": "Email Address Already Exists
    }
    ```

* **Sample Call:**

  ```
  curl -X POST \
    http://localhost:8080/users/addProfessor \
    -H 'Accept: */*' \
    -H 'Cache-Control: no-cache' \
    -H 'Connection: keep-alive' \
    -H 'Content-Type: application/json' \
    -H 'Host: localhost:8080' \
    -H 'Postman-Token: d5790436-4c39-427c-a864-fedf98827c38,188647b0-8a8b-4c02-95eb-45de24238607' \
    -H 'User-Agent: PostmanRuntime/7.15.0' \
    -H 'accept-encoding: gzip, deflate' \
    -H 'cache-control: no-cache' \
    -H 'content-length: 147' \
    -d '{
    "firstName" : "Jean",
    "lastName"  : "Desire",
    "password"  : "password",
    "email"     : "jean.desire@natuniv.edu",
    "rating"       : "3.95"
  }
  ```

* **Notes:**

  If any information is not available for the user being created keep the field in the request and provide a blank string as the value.
  
  
 
**Show Users Data**
----
  Returns the assigned user data for a users. Can be admin, professor or student data.

* **URL**

  `/user/{user_id}/userData`

* **Method:**
  
  `GET` 
  
*  **URL Params**


   **Required:**
 
   `user_id=[integer]`

   **Optional:**
 
   `None`

* **Data Params**

  `None`

* **Success Response:**


  * **Code:** `200`
    **Content:** 
    ```
    {
    "firstName": "Mike",
    "lastName": "Coopman",
    "email": "macoopman@gmail.com",
    "dateJoined": "2019-08-04T18:41:24.000+0000",
    "hibernateLazyInitializer": {},
    "_links": {
        "self": {
            "href": "http://localhost:8080/persons/902"
        },
        "person": {
            "href": "http://localhost:8080/persons/902"
        },
        "currentSession": {
            "href": "http://localhost:8080/persons/902/currentSession"
        },
        "questions": {
            "href": "http://localhost:8080/persons/902/questions"
        },
        "user": {
            "href": "http://localhost:8080/persons/902/user"
        }
      }
    }
    ```
 
* **Error Response:**

  * **Code:** `404 NOT FOUND`
    **Content:** `None`
 `

* **Sample Call:**

  ```
  curl -X GET \
    http://localhost:8080/users/909/userData \
    -H 'Accept: */*' \
    -H 'Cache-Control: no-cache' \
    -H 'Connection: keep-alive' \
    -H 'Content-Type: application/json' \
    -H 'Host: localhost:8080' \
    -H 'Postman-Token: 80a8997d-4485-4977-89d3-ec6ad82055d3,9feb66f4-a2b0-494e-bc90-ed15612c9bf3' \
    -H 'User-Agent: PostmanRuntime/7.15.0' \
    -H 'accept-encoding: gzip, deflate' \
    -H 'cache-control: no-cache' \
    -H 'content-length: 26'
  ```

 
 
 
**Show User Role (privileges)**
----
  Return JSON data showing users assigned roles

* **URL**

  `/users/{user_id}/roles`

* **Method:**

  `GET`
  
*  **URL Params**

   **Required:**
 
   `user_id=[integer]`

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
        "roles": [
            {
                "description": "User role",
                "roleName": "ROLE_USER",
                "authority": "ROLE_USER",
                "_links": {
                    "self": {
                        "href": "http://localhost:8080/roles/2"
                    },
                    "role": {
                        "href": "http://localhost:8080/roles/2"
                    }
                }
            }
        ]
    },
    "_links": {
        "self": {
            "href": "http://localhost:8080/users/902/roles"
        }
     }
    }
    ```
 
* **Error Response:**

  * **Code:** `404 NOT FOUND` 
    **Content:** None


* **Sample Call:**

  ```
  curl -X GET \
    http://localhost:8080/users/902/roles \
    -H 'Accept: */*' \
    -H 'Cache-Control: no-cache' \
    -H 'Connection: keep-alive' \
    -H 'Content-Type: application/json' \
    -H 'Host: localhost:8080' \
    -H 'Postman-Token: f4628960-e81f-4b1c-89f3-7f1389c21128,13c786a8-557c-44f9-84d9-9011a6b7734f' \
    -H 'User-Agent: PostmanRuntime/7.15.0' \
    -H 'accept-encoding: gzip, deflate' \
    -H 'cache-control: no-cache'
  ```



**Password Recovery (Phase 1)**
----
  Initiates the password recovery process. If valid sends an email to the user with a new temp password.

* **URL**

  `/user/recover`

* **Method:**
  
  `POST` 
  
*  **URL Params**

   **Required:**
 
  `None`


* **Data Params**

  ```
  {
    "username" : "MCoopman902",
    "email"    : "macoopman@gmail.com"
  }
  ```

* **Success Response:**
  

  * **Code:** `200` 
    **Content:** 
    ```
    {
    "userId": 902
    }
    ```
 
* **Error Response:**

  * **Code:** `406 Not Acceptable`
    **Content:** 
    ```
    {
      "timestamp": "2019-08-09T03:20:48.011+0000",
      "message": "Invalid Email Address"
    }
    ```
  OR
  
  * **Code:** `406 Not Acceptable`
    **Content:** 
    ```
    {
      "timestamp": "2019-08-09T03:20:48.011+0000",
      "message": "Invalid Username"
    }
    ```

* **Sample Call:**

  ```
  curl -X POST \
  http://localhost:8080/users/recover \
  -H 'Accept: */*' \
  -H 'Cache-Control: no-cache' \
  -H 'Connection: keep-alive' \
  -H 'Content-Type: application/json' \
  -H 'Host: localhost:8080' \
  -H 'Postman-Token: a4b1d59c-be45-4a5a-bbe1-ea41f6eab9aa,0e1dc567-5777-451c-99e6-b4c768fd7355' \
  -H 'User-Agent: PostmanRuntime/7.15.0' \
  -H 'accept-encoding: gzip, deflate' \
  -H 'cache-control: no-cache' \
  -H 'content-length: 32' \
  -d '{
	"username" : "MCoopman902",
	"email"    : "macoopman@gmail.com"
  }'
  ```

* **Notes:**

    Call requires, a minimum, of one of the key/value pairs. Both are validated in the API.
    
    

**Password Change (Phase 2)**
----
  Completes the password change given the temp password, user_id, and new password

* **URL**

  `/user/recover/passwordChange`
  
* **Method:**
   `POST` 
  
*  **URL Params**

    `None`

   **Required:**
 
   `None`

   **Optional:**
 
   `None`

* **Data Params**

  ```
  {
	"userId"       : "902",
	"tempPassword" : "q0GioQWqWX",
	"newPassword"  : "password123"
  }
  ```

* **Success Response:**
  

  * **Code:** `200` 
    **Content:**  None
 
* **Error Response:**

 * **Code:** `406 Not Acceptable`
    **Content:** 
    ```
    {
      "timestamp": "2019-08-09T03:20:48.011+0000",
      "message": "Invalid User Id"
    }
    ```
   OR
   
   * **Code:** `406 Not Acceptable`
    **Content:** 
    ```
    {
      "timestamp": "2019-08-09T03:20:48.011+0000",
      "message": "Invalid Temporary Password"
    }
    ```
  

* **Sample Call:**

  ```
  curl -X POST \
    http://localhost:8080/users/recover/passwordChange \
    -H 'Accept: */*' \
    -H 'Cache-Control: no-cache' \
    -H 'Connection: keep-alive' \
    -H 'Content-Type: application/json' \
    -H 'Host: localhost:8080' \
    -H 'Postman-Token: 725ed6a6-0051-4fa8-b8a9-0291ad895281,b041e2b8-2ff2-49a2-9d03-07b37441e555' \
    -H 'User-Agent: PostmanRuntime/7.15.0' \
    -H 'accept-encoding: gzip, deflate' \
    -H 'cache-control: no-cache' \
    -H 'content-length: 93' \
    -d '{
    "userId"       : "902",
    "tempPassword" : "q0GioQWqWX3",
    "newPassword"  : "password123"
  }'
  ```

**Update Learning Syle Question/Answer**
----
  Makes changes to the user learning style questions

* **URL**

 `/user/{userId}/updateLearningStyle/{learningStyleId}`

* **Method:**

   `POST` 
  
*  **URL Params**


   **Required:**
 
   `userId=[integer]`
   `learningStyleId=[integer]`

   **Optional:**
 
   `None`

* **Data Params**

  ```
  {
	"updatedValue" : "63"
  }
  ```

* **Success Response:**
  
  * **Code:** `200` 
    **Content:** None
 
* **Error Response:**

   * **Code:** `406 Not Acceptable`
      **Content:** 
      ```
      {
        "timestamp": "2019-08-09T03:20:48.011+0000",
        "message": "Invalid Learning Style Id"
      }
      ```
      OR
   * **Code:** `406 Not Acceptable`
      **Content:** 
      ```
      {
        "timestamp": "2019-08-09T03:20:48.011+0000",
        "message": "User Id Not Found"
      }
      ```   


* **Sample Call:**

  ```
  curl -X POST \
  http://localhost:8080/users/902/updateLearningStyle/10 \
  -H 'Accept: */*' \
  -H 'Cache-Control: no-cache' \
  -H 'Connection: keep-alive' \
  -H 'Content-Type: application/json' \
  -H 'Host: localhost:8080' \
  -H 'Postman-Token: d187b8b6-76f0-43d5-942f-97ad70ecb255,27984d2e-5ebe-42b7-be73-fe0bc742ecde' \
  -H 'User-Agent: PostmanRuntime/7.15.0' \
  -H 'accept-encoding: gzip, deflate' \
  -H 'cache-control: no-cache' \
  -H 'content-length: 26' \
  -d '{
	"updatedValue" : "63"
  }'
  ```

