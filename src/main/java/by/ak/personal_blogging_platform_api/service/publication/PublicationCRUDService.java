package by.ak.personal_blogging_platform_api.service.publication;

import java.util.List;
import by.ak.personal_blogging_platform_api.entity.contributionEntity.dto.PublicationDto;

public interface PublicationCRUDService {

	PublicationDto createPublication(PublicationDto publication);

    List<PublicationDto> getAllPublications();

    PublicationDto getPublicationById(Long id);

    PublicationDto updatePublication(Long id, PublicationDto publicationDetails);

    void deletePublication(Long id);

    PublicationDto publishPublication(Long id);

    PublicationDto unpublishPublication(Long id);
}