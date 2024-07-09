package by.ak.personal_blogging_platform_api.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.ak.personal_blogging_platform_api.dao.UserRepository;
import by.ak.personal_blogging_platform_api.entity.userEntity.Role;
import by.ak.personal_blogging_platform_api.entity.userEntity.User;
import by.ak.personal_blogging_platform_api.entity.userEntity.dto.UserCreationDto;
import by.ak.personal_blogging_platform_api.entity.userEntity.dto.UserDto;
import by.ak.personal_blogging_platform_api.service.Mapper;
import by.ak.personal_blogging_platform_api.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class RegistrationServiceImpl implements RegistrationService {

	private final UserRepository repository;
	private final Mapper<User, UserCreationDto> userCreationMapper;
	private final Mapper<User, UserDto> userDtoMapper;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	@Override
	public UserDto registration(UserCreationDto userCreationDto) {
		
		CharSequence password = "{bcrypt}" + passwordEncoder.encode(userCreationDto.password());
		log.debug("Password before encript: " + userCreationDto.password() + " password after encrypt: " + password);

		User user = userCreationMapper.toEntity(userCreationDto);
		user.setPassword(password.toString());
		user.setActive(true);
		user.setDateOfCreation(LocalDate.now());
		user.setRoles(List.of(Role.ROLE_USER));

		try {

			return userDtoMapper.toDto(repository.save(user));

		} catch (DataIntegrityViolationException e) {
			log.error("Failed to registration user: {}", e.getMessage());
			throw new IllegalArgumentException("Email address is already in use");

		} catch (Exception e) {
			log.error("Error registration user", e);
			throw new RuntimeException("Failed to register user", e);
		}
	}

}
