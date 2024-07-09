package by.ak.personal_blogging_platform_api.service.impl;

import by.ak.personal_blogging_platform_api.dao.UserRepository;
import by.ak.personal_blogging_platform_api.entity.userEntity.User;
import by.ak.personal_blogging_platform_api.entity.userEntity.dto.AdminUserDto;
import by.ak.personal_blogging_platform_api.service.AdminUserService;
import by.ak.personal_blogging_platform_api.service.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class AdminUserServiceImpl implements AdminUserService {

    private final UserRepository repository;

    private final Mapper<User, AdminUserDto> adminUserDtoMapper;

    @Override
    public AdminUserDto getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        return adminUserDtoMapper.toDto(repository.findByUsername(username).get());

    }

    @Override
    public AdminUserDto getUserById(long id) {

        return adminUserDtoMapper.toDto(
                repository.findById(id)
                        .orElseThrow(() -> new NoSuchElementException("User not found with id:" + id)));
    }

    @Override
    public List<AdminUserDto> getAllUsers() {

        return repository.findAll().stream()
                .map(user -> adminUserDtoMapper.toDto(user))
                .toList();
    }

    @Override
    @Transactional
    public void deleteUser(long id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
        } else {
            throw new NoSuchElementException("User is not exist with id:" + id);
        }

    }

    @Override
    @Transactional
    public AdminUserDto updateUser(long id, AdminUserDto user) {
        User existingUser = repository.findById(id).orElseThrow(() -> {
            log.error("User not exist with id: {}", id);
            return new NoSuchElementException("User not exist with id: " + id);
        });
        // All fields must already fill in userDto
        existingUser.setEmail(user.email());
        existingUser.setFirstname(user.firstname());
        existingUser.setLastname(user.lastname());
        existingUser.setUsername(user.username());

        if (Objects.nonNull(user.password())) {
            existingUser.setPassword(user.password());
        }
        existingUser.setActive(existingUser.isActive());
        existingUser.setDateOfCreation(existingUser.getDateOfCreation());

        if (Objects.nonNull(user.roles())) {
            existingUser.setRoles(user.roles());
        }

        return adminUserDtoMapper.toDto(repository.save(existingUser));

    }

    @Override
    public AdminUserDto findByUsername(String username) {

        return adminUserDtoMapper.toDto(
                repository.findByUsername(username)
                        .orElseThrow(() -> new NoSuchElementException("User not found with username:" + username)));
    }

    @Override
    @Transactional
    public void blockingUser(long id) {
        Optional<User> userForBlocking = repository.findById(id);

        if (userForBlocking.isPresent()) {
            userForBlocking.get().setActive(false);
            repository.save(userForBlocking.get());
        } else {
            throw new NoSuchElementException("User not found with id:" + id);
        }

    }

    @Override
    @Transactional
    public void unblockingUser(long id) {
        Optional<User> userForBlocking = repository.findById(id);

        if (userForBlocking.isPresent()) {
            userForBlocking.get().setActive(true);
            repository.save(userForBlocking.get());
        } else {
            throw new NoSuchElementException("User not found with id:" + id);
        }
    }


}
