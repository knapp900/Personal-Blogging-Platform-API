package by.ak.personal_blogging_platform_api.service.admin.impl;

import by.ak.personal_blogging_platform_api.entity.userEntity.User;
import by.ak.personal_blogging_platform_api.entity.userEntity.dto.AdminUserDto;
import by.ak.personal_blogging_platform_api.service.admin.AdminUserService;
import by.ak.personal_blogging_platform_api.service.mapper.Mapper;
import by.ak.personal_blogging_platform_api.service.user.UserCrudServiceOfRawEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminUserServiceImpl implements AdminUserService {

    private final UserCrudServiceOfRawEntity repository;

    private final Mapper<User, AdminUserDto> adminUserDtoMapper;

    @Override
    public AdminUserDto getCurrentUser() {
        return adminUserDtoMapper.toDto(
                repository.getCurrentUser());

    }

    @Override
    public AdminUserDto getUserById(long id) {

        return adminUserDtoMapper.toDto(
                repository.getUserById(id));
    }

    @Override
    public List<AdminUserDto> getAllUsers() {
        return repository.getAllUsers().stream()
                .map(adminUserDtoMapper::toDto)
                .toList();
    }

    @Override
    public void deleteUser(long id) {
        repository.deleteUser(id);

    }

    @Override
    public AdminUserDto updateUser(long id, AdminUserDto user) {
        return adminUserDtoMapper.toDto(
                repository.updateUser(id, adminUserDtoMapper.toEntity(user)));

    }

    @Override
    public AdminUserDto findByUsername(String username) {

        return adminUserDtoMapper.toDto(
                repository.findByUsername(username));
    }

    @Override
    public void blockingUser(long id) { repository.blockingUser(id);

    }

    @Override
    public void unblockingUser(long id) {
        repository.unblockingUser(id);
    }


}
