package com.d.urp.utils;

import com.d.urp.dto.DataDto;
import com.d.urp.dto.UserDto;
import com.d.urp.entity.Datas;
import com.d.urp.entity.User;

public class BeanUtils {
	public static User convertUserDtoToUser(UserDto userDto) {
		return User.builder().username(userDto.getUsername()).fullName(userDto.getFullName())
				.email(userDto.getEmail()).age(userDto.getAge()).gender(userDto.getGender())
				.password(userDto.getPassword()).build();
	}

	public static UserDto convertUserToUserDto(User user) {
		return UserDto.builder().userId(user.getUserId()).username(user.getUsername()).fullName(user.getFullName()).email(user.getEmail()).gender(user.getGender())
				.age(user.getAge()).build();
	}

	public static Datas convertDataDtoToEntity(DataDto dataDto) {
		return Datas.builder().key(dataDto.getKey()).value(dataDto.getValue()).build();
	}

	public static DataDto convertDataToDto(Datas datas) {
		return DataDto.builder().key(datas.getKey()).value(datas.getValue()).build();
	}
}
