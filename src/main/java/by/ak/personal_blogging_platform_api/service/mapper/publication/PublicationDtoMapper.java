package by.ak.personal_blogging_platform_api.service.mapper.publication;

import by.ak.personal_blogging_platform_api.service.user.UserCrudServiceOfRawEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import by.ak.personal_blogging_platform_api.entity.publcationEntity.Publication;
import by.ak.personal_blogging_platform_api.entity.publcationEntity.dto.PublicationDto;
import by.ak.personal_blogging_platform_api.service.mapper.Mapper;

import java.util.Objects;

@RequiredArgsConstructor
@Component
public class PublicationDtoMapper implements Mapper<Publication, PublicationDto> {

	private final UserCrudServiceOfRawEntity serviceOfRawEntity;

	@Override
	public Publication toEntity(PublicationDto dtoElement) {

		Publication publication = new Publication();
		publication.setId(dtoElement.id());
		publication.setTitle(dtoElement.title());
		publication.setUser(serviceOfRawEntity.getUserById(Objects.nonNull(dtoElement.userId())? dtoElement.userId():serviceOfRawEntity.getCurrentUser().getId()));
		publication.setContent(dtoElement.content());
		publication.setTags(dtoElement.tags());


		return publication;
	}

	@Override
	public PublicationDto toDto(Publication entity) {

		return new PublicationDto(
				entity.getId(),
				entity.getTitle(),
				entity.getContent(),
				entity.getUser().getId(),
				entity.getUser().getUsername(),
				entity.getTags());

	}

}
