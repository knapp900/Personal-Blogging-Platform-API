package by.ak.personal_blogging_platform_api.service.user.impl;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.ak.personal_blogging_platform_api.dao.UserRepository;
import by.ak.personal_blogging_platform_api.entity.userEntity.User;
import by.ak.personal_blogging_platform_api.entity.userEntity.dto.UserDto;
import by.ak.personal_blogging_platform_api.service.mapper.Mapper;
import by.ak.personal_blogging_platform_api.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
//TODO (High)-Убрать userRepository и заменить на userServiceOfRawEntity

	private final UserRepository repository;
	private final Mapper<User, UserDto> userDtoMapper;

	@Override
	public UserDto getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = ((UserDetails) authentication.getPrincipal()).getUsername();
		return userDtoMapper.toDto(repository.findByUsername(username).get());
		
	}

	@Override
	@Transactional
	public void deactivateUser(long id) {
		//FIXME Method throw "User not found with id" with correct ID!
		Optional<User> userForDeactivate = repository.findById(id);

		if (userForDeactivate.isPresent()) {
			userForDeactivate.get().setActive(false);
			repository.save(userForDeactivate.get());
		} else {
			throw new NoSuchElementException("User not found with id:" + id);
		}
	}

	@Override
	@Transactional
	public UserDto updateUser(long id, UserDto user) {
		User existingUser = repository.findById(id).orElseThrow(() -> {
			log.error("User not exist with id: {}", id);
			return new NoSuchElementException("User not exist with id: " + id);
		});
		// All fields must already fill in userDto
		existingUser.setEmail(user.email());
		existingUser.setFirstname(user.firstname());
		existingUser.setLastname(user.lastname());
		existingUser.setUsername(user.username());
		existingUser.setActive(existingUser.isActive());

		return userDtoMapper.toDto(repository.save(existingUser));

	}

	public UserDto findByUsername(String username) {

		return userDtoMapper.toDto(repository.findByUsername(username)
				.orElseThrow(() -> new NoSuchElementException("User not exist with username: " + username)));
	}

}
