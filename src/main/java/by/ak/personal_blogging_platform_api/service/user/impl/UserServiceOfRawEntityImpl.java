package by.ak.personal_blogging_platform_api.service.user.impl;

import by.ak.personal_blogging_platform_api.dao.UserRepository;
import by.ak.personal_blogging_platform_api.entity.userEntity.User;
import by.ak.personal_blogging_platform_api.entity.userEntity.dto.UserDto;
import by.ak.personal_blogging_platform_api.service.user.UserServiceOfRawEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceOfRawEntityImpl implements UserServiceOfRawEntity {

    private final UserRepository repository;

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        return repository.findByUsername(username).get();

    }
    @Override
    public User getUserById(long id) {
        return repository.findById(id).orElseThrow(() ->
        {
            log.error("User not found with id: {}", id);
            throw new NoSuchElementException("User not found with id: " + id);
        });
    }

    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public void deleteUser(long id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
        } else {
            throw new NoSuchElementException("User is not exist with id:" + id);
        }
    }

    @Override
    public User updateUser(long id, User user) {
        User existingUserForUpdate = repository.findById(id).orElseThrow(() -> {
            log.error("User not exist with id: {}", id);
            return new NoSuchElementException("User not exist with id: " + id);
        });
        existingUserForUpdate.setEmail(Objects.nonNull(user.getEmail()) ? user.getEmail() : existingUserForUpdate.getEmail());
        existingUserForUpdate.setFirstname(Objects.nonNull(user.getFirstname()) ? user.getFirstname() : existingUserForUpdate.getFirstname());
        existingUserForUpdate.setLastname(Objects.nonNull(user.getLastname()) ? user.getLastname() : existingUserForUpdate.getLastname());
        existingUserForUpdate.setUsername(Objects.nonNull(user.getUsername()) ? user.getUsername() : existingUserForUpdate.getUsername());
        existingUserForUpdate.setPassword(existingUserForUpdate.getPassword());
        existingUserForUpdate.setActive(existingUserForUpdate.isActive());
        existingUserForUpdate.setDateOfCreation(existingUserForUpdate.getDateOfCreation());
        existingUserForUpdate.setRoles(Objects.nonNull(user.getRoles()) ? user.getRoles() : existingUserForUpdate.getRoles());

        return repository.save(existingUserForUpdate);

    }

    @Override
    public User findByUsername(String username) {
        return repository.findByUsername(username).orElseThrow(() ->
        {
            log.error("User not found with username: {}", username);
            throw new NoSuchElementException("User not found with username: " + username);
        });
    }

    @Override
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
