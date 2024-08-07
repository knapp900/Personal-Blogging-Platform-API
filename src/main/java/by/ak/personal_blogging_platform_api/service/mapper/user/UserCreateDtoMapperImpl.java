package by.ak.personal_blogging_platform_api.service.mapper.user;

import org.springframework.stereotype.Component;

import by.ak.personal_blogging_platform_api.entity.userEntity.User;
import by.ak.personal_blogging_platform_api.entity.userEntity.dto.UserCreationDto;
import by.ak.personal_blogging_platform_api.service.mapper.Mapper;

@Component
public class UserCreateDtoMapperImpl implements Mapper<User, UserCreationDto> {

	@Override
	public User toEntity(UserCreationDto dtoElement) {
		User user = new User();
		user.setId(dtoElement.id());
		user.setUsername(dtoElement.username());
		user.setFirstname(dtoElement.firstname());
		user.setLastname(dtoElement.lastname());
		user.setPassword(dtoElement.password());
		user.setEmail(dtoElement.email());
		return user;
	}

	@Override
	public UserCreationDto toDto(User entity) {
		return new UserCreationDto(
				entity.getId(), 
				entity.getUsername(), 
				entity.getFirstname(), 
				entity.getLastname(),
				entity.getPassword(), 
				entity.getEmail());
	}

}
