package by.ak.personal_blogging_platform_api.service.user;

import by.ak.personal_blogging_platform_api.entity.userEntity.User;

import java.util.List;

public interface UserServiceOfRawEntity {

    User getCurrentUser();
    User getUserById(long id);

    List<User> getAllUsers();

    void deleteUser(long id);

    User updateUser(long id, User user);

    User findByUsername(String username);

    void blockingUser(long id);

    void unblockingUser(long id);
}
