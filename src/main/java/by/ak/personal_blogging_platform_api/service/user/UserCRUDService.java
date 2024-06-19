package by.ak.personal_blogging_platform_api.service.user;

import java.util.List;

import by.ak.personal_blogging_platform_api.entity.userEntity.User;

public interface UserCRUDService {

	User createUser(User user);

	User getUserById(long id);

	List<User> getAllUsers();

	boolean deleteUser(long id);

	User updateUser(long id, User user);
}