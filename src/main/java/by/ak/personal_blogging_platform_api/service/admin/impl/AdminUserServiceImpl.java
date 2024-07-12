package by.ak.personal_blogging_platform_api.service.admin.impl;

import by.ak.personal_blogging_platform_api.entity.userEntity.User;
import by.ak.personal_blogging_platform_api.entity.userEntity.dto.AdminUserDto;
import by.ak.personal_blogging_platform_api.service.admin.AdminUserService;
import by.ak.personal_blogging_platform_api.service.mapper.Mapper;
import by.ak.personal_blogging_platform_api.service.user.UserServiceOfRawEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class AdminUserServiceImpl implements AdminUserService {

    private final UserServiceOfRawEntity repository;

    private final Mapper<User, AdminUserDto> adminUserDtoMapper;

    @Override
    public AdminUserDto getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        return adminUserDtoMapper.toDto(repository.findByUsername(username));

    }

    @Override
    public AdminUserDto getUserById(long id) {

        return adminUserDtoMapper.toDto(
                repository.getUserById(id));
    }

    @Override
    public List<AdminUserDto> getAllUsers() {

        return repository.getAllUsers().stream()
                .map(user -> adminUserDtoMapper.toDto(user))
                .toList();
    }

    @Override
    @Transactional
    public void deleteUser(long id) {
        repository.deleteUser(id);

    }

    @Override
    @Transactional
    public AdminUserDto updateUser(long id, AdminUserDto user) {
        return adminUserDtoMapper
                .toDto(repository.updateUser(id, adminUserDtoMapper.toEntity(user)));

    }

    @Override
    public AdminUserDto findByUsername(String username) {

        return adminUserDtoMapper.toDto(
                repository.findByUsername(username));
    }

    @Override
    @Transactional
    public void blockingUser(long id) {
        repository.blockingUser(id);

    }

    @Override
    @Transactional
    public void unblockingUser(long id) {
        repository.unblockingUser(id);
    }


}
