package by.ak.personal_blogging_platform_api.entity.userEntity.dto;

import java.time.LocalDate;
import java.util.List;

import by.ak.personal_blogging_platform_api.entity.userEntity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserManagementDto(Long id,
		
		@NotNull
		@Size(min=2, max=30 , message = "Nickname size should be more 2 character and not more 30 character")
		String nickname,
		
		@NotNull
		@Size(min=2, max=30, message = "First name size should be more 2 character and not more 30 character")
		String firstname,
		
		@NotNull
		@Size(min=2, max=30, message = "Last name size should be more 2 character and not more 30 character")
		String lastname,
		
		@NotNull
		@Size(min=6,max=30, message = "Password size should be more 2 character and not more 30 character")
		String password,
		
		@NotNull
		@Email(message = "Email validation error")
		String email,
		
		LocalDate dateOfCreation,
		
		boolean isActive,
		
		List<Role> roles
		
		) {
	//TODO (High) UserManagementDto

}
