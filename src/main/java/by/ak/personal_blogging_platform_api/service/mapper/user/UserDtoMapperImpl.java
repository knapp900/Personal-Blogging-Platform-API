package by.ak.personal_blogging_platform_api.service.mapper.user;

import org.springframework.stereotype.Component;

import by.ak.personal_blogging_platform_api.entity.userEntity.User;
import by.ak.personal_blogging_platform_api.entity.userEntity.dto.UserDto;
import by.ak.personal_blogging_platform_api.service.mapper.Mapper;

@Component
public class UserDtoMapperImpl implements Mapper<User, UserDto> {

	@Override
	public User toEntity(UserDto dtoElement) {
		User user = new User();
		user.setId(dtoElement.id());
		user.setUsername(dtoElement.username());
		user.setFirstname(dtoElement.firstname());
		user.setLastname(dtoElement.lastname());
		user.setEmail(dtoElement.email());
		user.setPublications(dtoElement.publications());
		return user;
	}

	@Override
	public UserDto toDto(User entity) {
		return new UserDto(
				entity.getId(), 
				entity.getUsername(),
				entity.getFirstname(), 
				entity.getLastname(), 
				entity.getEmail(),
				entity.getPublications());
	}

}
