package by.ak.personal_blogging_platform_api.service.user;

import by.ak.personal_blogging_platform_api.entity.userEntity.dto.UserDto;

public interface UserService {

	UserDto getCurrentUser();

	UserDto updateUser(long id, UserDto user);

	void deactivateUser(long id);
//TODO Вынести метод findByUsername в отдельный интерфейс например  ServiceOfFindUser или нет
	UserDto findByUsername(String username);



}