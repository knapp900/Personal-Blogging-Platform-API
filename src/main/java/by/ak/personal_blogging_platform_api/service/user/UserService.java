package by.ak.personal_blogging_platform_api.service.user;

import by.ak.personal_blogging_platform_api.entity.userEntity.dto.UserDto;

public interface UserService {

    UserDto getCurrentUser();

    UserDto updateUser(UserDto updates);

    void deactivateUser();


}