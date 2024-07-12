package by.ak.personal_blogging_platform_api.service.publication;

import java.util.List;
import by.ak.personal_blogging_platform_api.entity.publcationEntity.dto.PublicationDto;

public interface PublicationUserService {

	PublicationDto createPublication(PublicationDto publication);

    List<PublicationDto> getAllOwnPublications();

    PublicationDto getOwnPublicationById(Long id);

    PublicationDto updateOwnPublication(Long id, PublicationDto publicationDetails);

    void deleteOwnPublication(Long id);

    PublicationDto publishPublication(Long id);

    PublicationDto unpublishPublication(Long id);
}