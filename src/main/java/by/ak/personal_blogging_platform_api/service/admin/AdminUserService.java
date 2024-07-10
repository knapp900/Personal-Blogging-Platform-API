package by.ak.personal_blogging_platform_api.service.admin;

import by.ak.personal_blogging_platform_api.entity.userEntity.dto.AdminUserDto;

import java.util.List;

public interface AdminUserService {

    AdminUserDto getCurrentUser();

    AdminUserDto getUserById(long id);

    List<AdminUserDto> getAllUsers();

    void deleteUser(long id);

    AdminUserDto updateUser(long id, AdminUserDto user);

    AdminUserDto findByUsername(String username);

    void blockingUser(long id);

    void unblockingUser(long id);
}
