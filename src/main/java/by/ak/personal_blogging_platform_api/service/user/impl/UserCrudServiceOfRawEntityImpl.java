package by.ak.personal_blogging_platform_api.service.user.impl;

import by.ak.personal_blogging_platform_api.dao.UserRepository;
import by.ak.personal_blogging_platform_api.entity.userEntity.User;
import by.ak.personal_blogging_platform_api.service.exceptions.user.UserNotFoundException;
import by.ak.personal_blogging_platform_api.service.exceptions.user.UserServiceException;
import by.ak.personal_blogging_platform_api.service.user.UserCrudServiceOfRawEntity;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserCrudServiceOfRawEntityImpl implements UserCrudServiceOfRawEntity {

    private final UserRepository repository;

    @Override
    public User getCurrentUser() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = ((UserDetails) authentication.getPrincipal()).getUsername();
            return repository.findByUsername(username).orElseThrow(() ->
                    new UserNotFoundException("User not found with username: " + username));
        } catch (Exception e) {
            log.error("Error getting current user", e);
            throw new UserServiceException("Failed to get current user", e);
        }
    }

    @Override
    public User getUserById(long id) {
        try {
            return repository.findById(id).orElseThrow(() -> {
                log.error("User not found with id: {}", id);
                return new UserNotFoundException("User not found with id: " + id);
            });
        } catch (Exception e) {
            log.error("Error getting user by id: {}", id, e);
            throw new UserServiceException("Failed to get user by id: " + id, e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            log.error("Error getting all users", e);
            throw new UserServiceException("Failed to get all users", e);
        }
    }

    @Override
    @Transactional
    public void deleteUser(long id) {
        try {
            if (repository.findById(id).isPresent()) {
                repository.deleteById(id);
            } else {
                throw new UserNotFoundException("User not found with id: " + id);
            }
        } catch (Exception e) {
            log.error("Error deleting user with id: {}", id, e);
            throw new UserServiceException("Failed to delete user with id: " + id, e);
        }
    }

    @Override
    @Transactional
    public User updateUser(long id, User user) {
        try {
            User existingUserForUpdate = repository.findById(id).orElseThrow(() -> {
                log.error("User not found with id: {}", id);
                return new UserNotFoundException("User not found with id: " + id);
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
        } catch (Exception e) {
            log.error("Error updating user with id: {}", id, e);
            throw new UserServiceException("Failed to update user with id: " + id, e);
        }
    }

    @Override
    public User findByUsername(String username) {
        try {
            return repository.findByUsername(username).orElseThrow(() -> {
                log.error("User not found with username: {}", username);
                return new UserNotFoundException("User not found with username: " + username);
            });
        } catch (Exception e) {
            log.error("Error finding user by username: {}", username, e);
            throw new UserServiceException("Failed to find user by username: " + username, e);
        }
    }

    @Override
    @Transactional
    public void blockingUser(long id) {
        try {
            User userForBlocking = repository.findById(id).orElseThrow(() ->
                    new UserNotFoundException("User not found with id: " + id));
            userForBlocking.setActive(false);
            repository.save(userForBlocking);
        } catch (Exception e) {
            log.error("Error blocking user with id: {}", id, e);
            throw new UserServiceException("Failed to block user with id: " + id, e);
        }
    }

    @Override
    @Transactional
    public void unblockingUser(long id) {
        try {
            User userForBlocking = repository.findById(id).orElseThrow(() ->
                    new UserNotFoundException("User not found with id: " + id));
            userForBlocking.setActive(true);
            repository.save(userForBlocking);
        } catch (Exception e) {
            log.error("Error unblocking user with id: {}", id, e);
            throw new UserServiceException("Failed to unblock user with id: " + id, e);
        }
    }
}
