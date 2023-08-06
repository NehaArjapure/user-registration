# user-registration
## Description
### Provide a brief overview of the project, including its purpose, main features, and any specific goals you have in mind.

## Technologies Used
### Spring Boot
### Spring Data JPA
### MySQL
### Other technologies used in the project (if any)
## Installation and Setup
### Java JDK (version 8 or higher)
### MySQL 
### Maven 

## Database Setup
### Create a new MySQL database.
### Update the database configuration in the application.properties file with the appropriate credentials and database name.
### Running the Application
### Use the following command to run the application:

### arduino
### Copy code
### mvn spring-boot:run
### The application will start, and you can access it at http://localhost:8080.

## API Documentation
## 1. User Registration
### Endpoint: POST /api/register

## Request

### json
### Copy code
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
### Copy code
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
### Copy code
### {
  "status": "error",
  "code": "INVALID_REQUEST",
  "message": "Invalid request. Please provide all required fields: username, email, password, full_name."
}
## 2. Generate Token
### Endpoint: POST /api/token

### Request

### json
### Copy code
### {
  "username": "example_user",
  "password": "secure_password123"
}
### Response

### json
### Copy code
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
### Copy code
### {
  "key": "unique_key",
  "value": "data_value"
}
### Response

### json
### Copy code
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
### Copy code
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
### Copy code
### {
  "value": "new_data_value"
}
### Response

### json
### Copy code
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
### Copy code
### {
  "status": "success",
  "message": "Data deleted successfully."
}
## How to Use
### Interact with the APIs using tools like Postman. 
