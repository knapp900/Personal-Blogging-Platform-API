package by.ak.personal_blogging_platform_api.entity.publcationEntity.dto;

import java.util.List;

import by.ak.personal_blogging_platform_api.entity.publcationEntity.Tag;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PublicationDto(

		Long id,
		
		@Size(min=2, max=256)
		String title,
				
		@NotNull
		String content,

		Long userId,

		String username,

		List<Tag> tags

) {

}
