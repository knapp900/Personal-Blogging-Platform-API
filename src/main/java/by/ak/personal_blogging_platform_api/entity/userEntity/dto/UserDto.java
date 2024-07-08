package by.ak.personal_blogging_platform_api.entity.userEntity.dto;


import java.util.Set;

import by.ak.personal_blogging_platform_api.entity.contributionEntity.Publication;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserDto(
		
		Long id,
		
		@NotNull
		@Size(min=2, max=30 , message = "Nickname size should be more 2 character and not more 30 character")
		String username,
		
		@NotNull
		@Size(min=2, max=30, message = "First name size should be more 2 character and not more 30 character")
		String firstname,
		
		@NotNull
		@Size(min=2, max=30, message = "Last name size should be more 2 character and not more 30 character")
		String lastname,
		
		@NotNull
		@Email(message = "Email validation error")
		String email,
		
		Set<Publication> publications)

		{
}