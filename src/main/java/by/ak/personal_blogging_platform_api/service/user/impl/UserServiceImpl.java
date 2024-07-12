package by.ak.personal_blogging_platform_api.service.user.impl;

import by.ak.personal_blogging_platform_api.service.user.UserCrudServiceOfRawEntity;
import org.springframework.stereotype.Service;

import by.ak.personal_blogging_platform_api.entity.userEntity.User;
import by.ak.personal_blogging_platform_api.entity.userEntity.dto.UserDto;
import by.ak.personal_blogging_platform_api.service.mapper.Mapper;
import by.ak.personal_blogging_platform_api.service.user.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
	private final Mapper<User, UserDto> userDtoMapper;

	private final UserCrudServiceOfRawEntity repository;

	@Override
	public UserDto getCurrentUser() {
		return userDtoMapper.toDto(
				repository.getCurrentUser());

	}

	@Override
	public void deactivateUser() {
		repository.blockingUser(
				repository.getCurrentUser().getId());
	}

	@Override
	public UserDto updateUser(UserDto updates) {
		return userDtoMapper.toDto(
				repository.updateUser(repository.getCurrentUser().getId(),userDtoMapper.toEntity(updates)));

	}


}
