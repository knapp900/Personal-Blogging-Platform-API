package by.ak.personal_blogging_platform_api.service.user;

import java.util.List;

import by.ak.personal_blogging_platform_api.entity.userEntity.dto.UserCreationDto;
import by.ak.personal_blogging_platform_api.entity.userEntity.dto.UserDto;

public interface UserCRUDService {

	UserDto createUser(UserCreationDto userCreationDto);

	UserDto getUserById(long id);

	List<UserDto> getAllUsers();

	void deleteUser(long id);

	UserDto updateUser(long id, UserCreationDto user);
}