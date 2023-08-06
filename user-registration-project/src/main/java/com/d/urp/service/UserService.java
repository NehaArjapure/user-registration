package com.d.urp.service;

import com.d.urp.dto.DataDto;
import com.d.urp.dto.LoginDto;
import com.d.urp.dto.TokenDto;
import com.d.urp.dto.UserDto;

public interface UserService {

	UserDto userRegistration(UserDto userDto);

	TokenDto login(LoginDto loginDto);

	String saveData(String token,DataDto dataDto);

	DataDto data(String jwtToken, String key);

	String update(String jwtToken, String key,DataDto dataDto);

	String delete(String jwtToken, String key);

}
