# user-registration
## Description
### This Project Only For compliting a Task

## Technologies Used 
### Spring Boot
### Spring Data JPA
### MySQL

## Installation and Setup
### Java JDK (version 8 or higher)
### MySQL 
### Maven 

## Database Setup
### Create a new MySQL database.
### Update the database configuration in the application.properties file with the appropriate credentials and database name.
### Running the Application
### Use the following command to run the application:

### Copy code
### mvn spring-boot:run
### The application will start, and you can access it at http://localhost:8080.

## API Documentation
## 1. User Registration
### Endpoint: POST /api/register

## Request

### json
### 
### {
  "username": "example_user",
  "email": "user@example.com",
  "password": "secure_password123",
  "full_name": "John Doe",
  "age": 30,
  "gender": "male"
}
### Success Response

### json
###
### {
  "status": "success",
  "message": "User successfully registered!",
  "data": {
    "user_id": "12345",
    "username": "example_user",
    "email": "user@example.com",
    "full_name": "John Doe",
    "age": 30,
    "gender": "male"
  }
}
### Error Response

### json
### 
### {
  "status": "error",
  "code": "INVALID_REQUEST",
  "message": "Invalid request. Please provide all required fields: username, email, password, full_name."
}
## 2. Generate Token
### Endpoint: POST /api/token

### Request

### json
### 
### {
  "username": "example_user",
  "password": "secure_password123"
}
### Response

### json
###
### {
  "status": "success",
  "message": "Access token generated successfully.",
  "data": {
    "access_token": "<TOKEN>",
    "expires_in": 3600
  }
}
## 3. Store Data
### Endpoint: POST /api/data

### Request Headers:

### Authorization: Bearer access_token
### json
###
### {
  "key": "unique_key",
  "value": "data_value"
}
### Response

### json
###
### {
  "status": "success",
  "message": "Data stored successfully."
}
## 4. Retrieve Data
### Endpoint: GET /api/data/{key}

### Request Headers:

### Authorization: Bearer access_token
### Response

### json
### 
### {
  "status": "success",
  "data": {
    "key": "unique_key",
    "value": "data_value"
  }
}
## 5. Update Data
### Endpoint: PUT /api/data/{key}

### Request Headers:

### Authorization: Bearer access_token
### Request

### json
### 
### {
  "value": "new_data_value"
}
### Response

### json
###
### {
  "status": "success",
  "message": "Data updated successfully."
}
## 6. Delete Data
### Endpoint: DELETE /api/data/{key}

### Request Headers:

### Authorization: Bearer access_token
### Response

### json
###
### {
  "status": "success",
  "message": "Data deleted successfully."
}
## How to Use
### Interact with the APIs using tools like Postman. 
