package by.ak.personal_blogging_platform_api.service.common;

import by.ak.personal_blogging_platform_api.entity.userEntity.dto.UserCreationDto;
import by.ak.personal_blogging_platform_api.entity.userEntity.dto.UserDto;

public interface RegistrationService {
	
	UserDto registration (UserCreationDto userCreationDto);

}
