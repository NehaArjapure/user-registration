package com.d.urp.exception.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.d.urp.exception.UserException;
import com.d.urp.response.ErrorResponse;

@RestControllerAdvice
public class ExceptionHandlers {
	Map<String, String> errorMassage = new HashMap<>();

	@ExceptionHandler(value = { UserException.class })
	public ErrorResponse handler(RuntimeException run) {
		
		errorMassage.put("INVALID_REQUEST",
				"Invalid request. Please provide all required fields: username, email, password, full_name.");
		errorMassage.put("USERNAME_EXISTS",
				"The provided username is already taken. Please choose a different username.");
		errorMassage.put("EMAIL_EXISTS",
				"The provided email is already registered. Please use a different email address.");
		errorMassage.put("INVALID_PASSWORD",
				"The provided password does not meet the requirements. Password must be at least 8 characters long and contain a mix of uppercase and lowercase letters, numbers, and special characters.");
		errorMassage.put("INVALID_AGE", "Invalid age value. Age must be a positive integer.");
		errorMassage.put("GENDER_REQUIRED",
				"Gender field is required. Please specify the gender (e.g., male, female, non-binary).");
		errorMassage.put("INTERNAL_SERVER_ERROR", "An internal server error occurred. Please try again later.");
		errorMassage.put("INVALID_CREDENTIALS", "Invalid credentials. The provided username or password is incorrect.");
		errorMassage.put("MISSING_FIELDS", "Missing fields. Please provide both username and password.");
		errorMassage.put("INTERNAL_ERROR", "Internal server error occurred. Please try again later.");
		errorMassage.put("INVALID_KEY", "The provided key is not valid or missing.");
		errorMassage.put("INVALID_VALUE", " The provided value is not valid or missing");
		errorMassage.put("KEY_EXISTS",
				"The provided key already exists in the database. To update an existing key, use the update API.");
		errorMassage.put("INVALID_TOKEN", "Invalid access token provided");
		errorMassage.put("KEY_NOT_FOUND", " The provided key does not exist in the database.");
		
		return ErrorResponse.builder().status("error").code(run.getMessage()).massage(errorMassage.get(run.getMessage())).build();
	}

}
