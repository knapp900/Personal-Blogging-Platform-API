package by.ak.personal_blogging_platform_api.mapper.publication;

import org.springframework.stereotype.Component;

import by.ak.personal_blogging_platform_api.entity.contributionEntity.Publication;
import by.ak.personal_blogging_platform_api.entity.contributionEntity.dto.PublicationDto;
import by.ak.personal_blogging_platform_api.service.Mapper;

@Component
public class PublicationDtoMapper implements Mapper<Publication, PublicationDto> {

	@Override
	public Publication toEntity(PublicationDto dtoElement) {

		Publication publication = new Publication();
		publication.setId(dtoElement.id());
		publication.setTitle(dtoElement.title());
		publication.setAuthors(dtoElement.authors());
		publication.setContent(dtoElement.content());

		return publication;
	}

	@Override
	public PublicationDto toDto(Publication entity) {

		return new PublicationDto(
				entity.getId(), 
				entity.getTitle(), 
				entity.getAuthors(), 
				entity.getContent());

	}

}
