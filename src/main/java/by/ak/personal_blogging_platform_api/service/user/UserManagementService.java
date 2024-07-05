package by.ak.personal_blogging_platform_api.service.user;

import java.util.List;

import by.ak.personal_blogging_platform_api.entity.userEntity.dto.UserManagementDto;

public interface UserManagementService {
	
	//TODO (High) UserManagementService
	
	UserManagementDto getUserDetails(Long id);

	UserManagementDto updateUserRoles(Long id, List<String> roles);

	void deactivateUser(Long id);
}
