package by.ak.personal_blogging_platform_api.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.ak.personal_blogging_platform_api.dao.UserRepository;
import by.ak.personal_blogging_platform_api.entity.userEntity.User;
import by.ak.personal_blogging_platform_api.entity.userEntity.dto.UserCreationDto;
import by.ak.personal_blogging_platform_api.entity.userEntity.dto.UserDto;
import by.ak.personal_blogging_platform_api.service.AdminUserService;
import by.ak.personal_blogging_platform_api.service.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class AdminUserServiceImpl implements AdminUserService {

	private final UserRepository repository;
	private final Mapper<User, UserDto> userDtoMapper;

	@Override
	public UserDto getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = ((UserDetails) authentication.getPrincipal()).getUsername();
		return userDtoMapper.toDto(repository.findByUsername(username).get());

	}

	@Override
	public UserDto getUserById(long id) {

		return userDtoMapper.toDto(
				repository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("User not found with id:" + id)));
	}

	@Override
	public List<UserDto> getAllUsers() {
		
		return repository.findAll().stream()
				.map(user -> userDtoMapper.toDto(user))
				.toList();
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
	@Transactional
	public UserDto updateUser(long id, UserCreationDto user) {
		User existingUser = repository.findById(id).orElseThrow(() -> {
			log.error("User not exist with id: {}", id);
			return new NoSuchElementException("User not exist with id: " + id);
		});
		// All fields must already fill in userDto
		existingUser.setEmail(user.email());
		existingUser.setFirstname(user.firstname());
		existingUser.setLastname(user.lastname());
		existingUser.setUsername(user.username());
		existingUser.setPassword(user.password());
		existingUser.setActive(existingUser.isActive());
		existingUser.setDateOfCreation(existingUser.getDateOfCreation());

		return userDtoMapper.toDto(repository.save(existingUser));

	}

	@Override
	public UserDto findByUsername(String username) {
		
		return userDtoMapper.toDto(
				repository.findByUsername(username)
				.orElseThrow(() -> new NoSuchElementException("User not found with username:" + username)));
	}

	@Override
	public String blockingUser(long id) {
		
		Optional<User> userForBlocking = repository.findById(id);
		
		if (userForBlocking.isPresent()) {
			userForBlocking.get().setActive(false);
			repository.save(userForBlocking.get());
			return "User has been blocked.";
		} else {
			throw new NoSuchElementException("User not found with id:" + id);
		}
		
	}
	
	

}
