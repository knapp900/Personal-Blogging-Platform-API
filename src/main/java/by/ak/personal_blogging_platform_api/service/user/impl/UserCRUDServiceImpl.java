package by.ak.personal_blogging_platform_api.service.user.impl;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.ak.personal_blogging_platform_api.dao.UserRepository;
import by.ak.personal_blogging_platform_api.entity.userEntity.Role;
import by.ak.personal_blogging_platform_api.entity.userEntity.User;
import by.ak.personal_blogging_platform_api.entity.userEntity.dto.UserCreationDto;
import by.ak.personal_blogging_platform_api.entity.userEntity.dto.UserDto;
import by.ak.personal_blogging_platform_api.service.user.Mapper;
import by.ak.personal_blogging_platform_api.service.user.UserCRUDService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Service
@Slf4j
public class UserCRUDServiceImpl implements UserCRUDService {

	private final UserRepository repository;
	private final Mapper<User, UserCreationDto> userCreationMapper;
	private final Mapper<User, UserDto> userDtoMapper;

	@Override
	@Transactional
	public UserDto createUser(UserCreationDto userCreationDto) {

		User user = userCreationMapper.toEntity(userCreationDto);
		// TODO (High) Add encrypt to password
		user.setActive(true);
		user.setDateOfCreation(LocalDate.now());
		user.setRoles(List.of(Role.USER));

		try {

			return userDtoMapper.toDto(repository.save(user));

		} catch (DataIntegrityViolationException e) {
			log.error("Failed to create user: {}", e.getMessage());
			throw new IllegalArgumentException("Email address is already in use");

		} catch (Exception e) {
			log.error("Error creating user", e);
			throw new RuntimeException("Failed to create user", e);
		}
	}

	@Override
	public UserDto getUserById(long id) {
		return userDtoMapper.toDto(repository.findById(id).orElseThrow(() -> {
			log.error("User not found with id: {}", id);
			return new NoSuchElementException("User not found with id: " + id);
		}));
	}

	@Override
	public List<UserDto> getAllUsers() {
		
		return repository.findAll().stream()
				.map(u -> userDtoMapper.toDto(u))
				.toList();
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
	public UserDto updateUser(long id, UserCreationDto user) {
		User existingUser = repository.findById(id).orElseThrow(() -> {
			log.error("User not exist with id: {}", id);
			return new NoSuchElementException("User not exist with id: " + id);
		});

		existingUser.setEmail(user.email());
		existingUser.setFirstname(user.firstname());
		existingUser.setLastname(user.lastname());
		existingUser.setNickname(user.nickname());
		existingUser.setPassword(user.password());
		existingUser.setActive(existingUser.isActive());
		existingUser.setDateOfCreation(existingUser.getDateOfCreation());

		return userDtoMapper.toDto(repository.save(existingUser));

	}
}
