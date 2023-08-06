package com.d.urp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.d.urp.dto.DataDto;
import com.d.urp.dto.LoginDto;
import com.d.urp.dto.TokenDto;
import com.d.urp.dto.UserDto;
import com.d.urp.response.SuccessResponse;
import com.d.urp.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api")
public class UserController {
	private final UserService userService;

	@PostMapping(path = "/register")
	public ResponseEntity<SuccessResponse<UserDto>> userRegistration(@RequestBody UserDto userDto) {
		UserDto userDto2 = userService.userRegistration(userDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(SuccessResponse.<UserDto>builder().status("success")
				.message("User successfully registered").data(userDto2).build());
	}

	@PostMapping("/token")
	public ResponseEntity<SuccessResponse<TokenDto>> login(@RequestBody LoginDto loginDto) {
		TokenDto tokenDto = userService.login(loginDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(SuccessResponse.<TokenDto>builder().status("success")
				.message("Access token generated successfully.").data(tokenDto).build());

	}
	
	@PostMapping(path="/savedata")
	public ResponseEntity<SuccessResponse<String>> saveData(HttpServletRequest request,@RequestBody DataDto dataDto) {
		
		String token=request.getHeader("Authorization");
		String jwtToken = token.substring(7);
		System.out.println(jwtToken);
		String massages=userService.saveData(jwtToken,dataDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(SuccessResponse.<String>builder().status("success")
				.message(massages).build());

	}
	
	@GetMapping(path="/data")
	public ResponseEntity<SuccessResponse<DataDto>> data(HttpServletRequest request,@PathParam("key") String key) {
		
		String token=request.getHeader("Authorization");
		String jwtToken = token.substring(7);
		System.out.println(jwtToken);
		DataDto dataDto=userService.data(jwtToken,key);
		return ResponseEntity.status(HttpStatus.CREATED).body(SuccessResponse.<DataDto>builder().status("success")
				.data(dataDto).build());

	}
	
	@PutMapping(path="/data")
	public ResponseEntity<SuccessResponse<String>> update(HttpServletRequest request,@PathParam("key") String key,@RequestBody DataDto dataDto) {
		
		String token=request.getHeader("Authorization");
		String jwtToken = token.substring(7);
		System.out.println(jwtToken);
		String massages=userService.update(jwtToken,key,dataDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(SuccessResponse.<String>builder().status("success")
				.message(massages).build());

	}
	
	@DeleteMapping(path="/data")
	public ResponseEntity<SuccessResponse<String>> delete(HttpServletRequest request,@PathParam("key") String key) {
		
		String token=request.getHeader("Authorization");
		String jwtToken = token.substring(7);
		System.out.println(jwtToken);
		String massages=userService.delete(jwtToken,key);
		return ResponseEntity.status(HttpStatus.CREATED).body(SuccessResponse.<String>builder().status("success")
				.message(massages).build());

	}
	
	

}
