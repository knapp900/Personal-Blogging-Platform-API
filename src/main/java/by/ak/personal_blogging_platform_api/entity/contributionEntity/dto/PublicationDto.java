package by.ak.personal_blogging_platform_api.entity.contributionEntity.dto;

import java.util.List;

import by.ak.personal_blogging_platform_api.entity.contributionEntity.Author;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PublicationDto(
		
		
		Long id,
		
		@Size(min=2, max=256)
		String title, 
		
		@NotNull
		List<Author> authors, 
				
		@NotNull
		String content

) {

}
