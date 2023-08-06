package com.d.urp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.d.urp.dto.DataDto;
import com.d.urp.dto.LoginDto;
import com.d.urp.dto.TokenDto;
import com.d.urp.dto.UserDto;
import com.d.urp.entity.Datas;
import com.d.urp.entity.User;
import com.d.urp.exception.UserException;
import com.d.urp.repository.DatasRepository;
import com.d.urp.repository.UserRepository;
import com.d.urp.utils.BeanUtils;
import com.d.urp.utils.jwt.JwtUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;
	private final DatasRepository datasRepository;
	private List<String> tokens = new ArrayList<>();

	@Override
	public UserDto userRegistration(UserDto userDto) {

		if (userDto.getUsername() != null && userDto.getFullName() != null && userDto.getEmail() != null) {
			Optional<User> username = userRepository.findByUsername(userDto.getUsername());
			if (!username.isPresent()) {
				Optional<User> findByEmail = userRepository.findByEmail(userDto.getEmail());
				if (!findByEmail.isPresent()) {
					if (userDto.getPassword().length() >= 8 && isValidPassword(userDto.getPassword())) {
						if (userDto.getAge() > 0) {
							if (userDto.getGender() != null) {
								User user = BeanUtils.convertUserDtoToUser(userDto);
								userRepository.save(user);
								return BeanUtils.convertUserToUserDto(user);
							}
							throw new UserException("GENDER_REQUIRED");
						}
						throw new UserException("INVALID_AGE");
					}
					throw new UserException("INVALID_PASSWORD");
				}
				throw new UserException("EMAIL_EXISTS");
			}
			throw new UserException("USERNAME_EXISTS");
		}
		throw new UserException("INVALID_REQUEST");
	}

	public static boolean isValidPassword(String password) {

		String uppercaseRegex = ".*[A-Z].*";
		String specialCharRegex = ".*[^A-Za-z0-9].*";
		String numberRegex = ".*\\d.*";

		boolean hasUppercase = password.matches(uppercaseRegex);
		boolean hasSpecialChar = password.matches(specialCharRegex);
		boolean hasNumber = password.matches(numberRegex);

		return hasUppercase && hasSpecialChar && hasNumber;
	}

	private final JwtUtils jwtUtils;

	@Override
	public TokenDto login(LoginDto loginDto) {
		if (loginDto.getUsername() != null && loginDto.getPassword() != null) {
			Optional<User> username = userRepository.findByUsernameAndPassword(loginDto.getUsername(),
					loginDto.getPassword());
			if (username.isPresent()) {
				String token = jwtUtils.generateToken(loginDto.getUsername());
				tokens.add(token);
				return TokenDto.builder().access_token(token).expires_in(3600).build();
			}
			throw new UserException("INVALID_CREDENTIALS");
		}
		throw new UserException("MISSING_FIELDS");
	}

	@Override
	public String saveData(String token, DataDto dataDto) {
		if (dataDto.getKey() != null) {
			if (dataDto.getValue() != null) {
				Optional<Datas> key = datasRepository.findByKey(dataDto.getKey());
				if (!key.isPresent()) {
					if (tokens.contains(token)) {
						Datas data = BeanUtils.convertDataDtoToEntity(dataDto);
						datasRepository.save(data);
						return "Data stored successfully.";
					}
					throw new UserException("INVALID_TOKEN");
				}
				throw new UserException("KEY_EXISTS");
			}
			throw new UserException("INVALID_VALUE");
		}
		throw new UserException("INVALID_KEY");

	}

	@Override
	public DataDto data(String jwtToken, String key) {
		Optional<Datas> keys = datasRepository.findByKey(key);
		if (keys.isPresent()) {
			if (tokens.contains(jwtToken)) {
				Datas datas = keys.get();
				return BeanUtils.convertDataToDto(datas);
			}
			throw new UserException("INVALID_TOKEN");
		}
		throw new UserException("KEY_NOT_FOUND");
	}

	@Override
	public String update(String jwtToken, String key,DataDto dataDto) {
		Optional<Datas> keys = datasRepository.findByKey(key);
		if (keys.isPresent()) {
			if (tokens.contains(jwtToken)) {
				Datas datas = keys.get();
				datas.setValue(dataDto.getValue());
				datasRepository.save(datas);
				return "Data updated successfully.";
			}
			throw new UserException("INVALID_TOKEN");
		}
		throw new UserException("KEY_NOT_FOUND");
	}

	@Override
	public String delete(String jwtToken, String key) {
		Optional<Datas> keys = datasRepository.findByKey(key);
		if (keys.isPresent()) {
			if (tokens.contains(jwtToken)) {
				Datas datas = keys.get();
				
				datasRepository.delete(datas);
				return "Data deleted successfully.";
			}
			throw new UserException("INVALID_TOKEN");
		}
		throw new UserException("KEY_NOT_FOUND");	}

}
