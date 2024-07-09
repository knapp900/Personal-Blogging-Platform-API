package by.ak.personal_blogging_platform_api.service;

import by.ak.personal_blogging_platform_api.entity.userEntity.dto.UserDto;

public interface UserService {
	UserDto getCurrentUser();
	UserDto updateUser(Long id, UserDto userDto);
}
