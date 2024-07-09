package by.ak.personal_blogging_platform_api.service;

import java.util.List;

import by.ak.personal_blogging_platform_api.entity.userEntity.dto.UserCreationDto;
import by.ak.personal_blogging_platform_api.entity.userEntity.dto.UserDto;

public interface AdminUserService {

	UserDto getCurrentUser();

	UserDto getUserById(long id);

	List<UserDto> getAllUsers();

	void deleteUser(long id);

	UserDto updateUser(long id, UserCreationDto user);

	UserDto findByUsername(String username);
	
	String blockingUser(long id);
}
