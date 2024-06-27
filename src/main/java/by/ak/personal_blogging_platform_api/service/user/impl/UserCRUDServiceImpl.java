package by.ak.personal_blogging_platform_api.service.user.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.ak.personal_blogging_platform_api.dao.UserRepository;
import by.ak.personal_blogging_platform_api.entity.userEntity.Role;
import by.ak.personal_blogging_platform_api.entity.userEntity.User;
import by.ak.personal_blogging_platform_api.service.user.UserCRUDService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Service
@Slf4j
public class UserCRUDServiceImpl implements UserCRUDService {

    private final UserRepository repository;

    @Override
    @Transactional
    public User createUser(User user) {
        try {
            user.setActive(true);
            user.setDateOfCreation(LocalDate.now());
            user.setRoles(List.of(Role.USER));
            return repository.save(user);
        } catch (Exception e) {
            log.error("Error creating user", e);
            throw e;
        }
    }

    @Override
    public User getUserById(long id) {
        return repository.findById(id)
                .orElseThrow(() -> {
                    log.error("User not found with id: {}", id);
                    return new NoSuchElementException("User not found with id: " + id);
                });
    }

    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public void deleteUser(long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            log.error("User not found with id: {}", id);
            throw new NoSuchElementException("User not found with id: " + id);
        }
    }

    @Override
    @Transactional
    public User updateUser(long id, User user) {
        User existingUser = repository.findById(id)
                .orElseThrow(() -> {
                    log.error("User not found with id: {}", id);
                    return new NoSuchElementException("User not found with id: " + id);
                });

        existingUser.setEmail(user.getEmail());
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setNickname(user.getNickname());
        existingUser.setPassword(user.getPassword());
        existingUser.setActive(existingUser.isActive());
        existingUser.setDateOfCreation(existingUser.getDateOfCreation());

        return repository.save(existingUser);
    }
}
