package com.d.urp.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserDto {

	private int userId;
	private String username;
	private String password;
	private String email;
	private String fullName;
	private int age;
	private String gender;
}
